package kr.sdbk.designsystem.di

import android.content.Context
import coil3.ImageLoader
import coil3.disk.DiskCache
import coil3.disk.directory
import coil3.memory.MemoryCache
import coil3.request.crossfade
import coil3.util.DebugLogger
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kr.sdbk.android_extensions.AppConfig
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
internal class UtilsModule {
    @Module
    @InstallIn(SingletonComponent::class)
    internal class UtilsModuleProvide {
        @Provides
        @Singleton
        fun providesImageLoader(
            appConfig: AppConfig,
            @ApplicationContext context: Context
        ) = ImageLoader.Builder(context)
            .crossfade(true)
            .memoryCache {
                MemoryCache.Builder()
                    .maxSizePercent(context, 0.25)
                    .build()
            }
            .diskCache {
                DiskCache.Builder()
                    .directory(context.cacheDir.resolve("image_cache"))
                    .maxSizePercent(0.02)
                    .build()
            }
            .also {
                if (appConfig.isDebuggable) it.logger(DebugLogger())
            }.build()
    }
}