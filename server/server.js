const express = require('express');
const fetch = require('node-fetch'); // npm install node-fetch
const admin = require('firebase-admin');
const cors = require('cors');
const app = express();

app.use(cors());
app.use(express.json());

// Firebase Admin 초기화
const serviceAccount = require('./serviceAccountKey.json');
admin.initializeApp({
  credential: admin.credential.cert(serviceAccount),
});

// Naver/Kakao userId 가져오기 함수
async function getUserId(accessToken, socialType) {
  if (socialType === 'naver') {
    const res = await fetch('https://openapi.naver.com/v1/nid/me', {
      headers: { Authorization: `Bearer ${accessToken}` },
    });
    const data = await res.json();
    if (data.response && data.response.id) return `naver_${data.response.id}`;
    throw new Error('Naver token invalid');
  } else if (socialType === 'kakao') {
    const res = await fetch('https://kapi.kakao.com/v2/user/me', {
      headers: { Authorization: `Bearer ${accessToken}` },
    });
    const data = await res.json();
    if (data.id) return `kakao_${data.id}`;
    throw new Error('Kakao token invalid');
  } else {
    throw new Error('Unsupported socialType');
  }
}

// Custom Token 생성 API
app.post('/getCustomToken', async (req, res) => {
  try {
    const { accessToken, socialType } = req.body;
    if (!accessToken || !socialType)
      return res.status(400).json({ error: 'accessToken & socialType required' });

    // 소셜 토큰으로 userId 가져오기
    const uid = await getUserId(accessToken, socialType);

    // Firebase Custom Token 생성
    const customToken = await admin.auth().createCustomToken(uid);

    res.json({ token: customToken });
  } catch (error) {
    console.error(error);
    res.status(500).json({ error: error.message });
  }
});

// 서버 실행
const PORT = process.env.PORT || 3000;
app.listen(PORT, () => {
  console.log(`Server running on port ${PORT}`);
});
