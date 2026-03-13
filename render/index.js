const express = require('express');
const admin = require('firebase-admin');
const cors = require('cors');
const app = express();

// CORS 설정 (앱에서 호출 가능하도록)
app.use(cors());
app.use(express.json());

// Firebase Admin 초기화
const serviceAccount = require('./serviceAccountKey.json');

admin.initializeApp({
  credential: admin.credential.cert(serviceAccount),
});

// Custom Token 생성 API
app.post('/getCustomToken', async (req, res) => {
  try {
    const { uid } = req.body;
    if (!uid) return res.status(400).json({ error: 'uid required' });

    const customToken = await admin.auth().createCustomToken(uid);
    res.json({ token: customToken });
  } catch (error) {
    console.error(error);
    res.status(500).json({ error: 'Internal server error' });
  }
});

// 포트 지정 (Render 환경변수 PORT 사용)
const PORT = process.env.PORT || 3000;
app.listen(PORT, () => {
  console.log(`Server running on port ${PORT}`);
});
