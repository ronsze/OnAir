const express = require('express');
const fetch = require('node-fetch'); // npm install node-fetch
const admin = require('firebase-admin');
const cors = require('cors');
const app = express();

app.use(cors());
app.use(express.json());

// 로그인 리다이렉트 페이지
app.get('/:authType/callback', (req, res) => {
  const { authType } = req.params;
  const { code, state, error, error_description } = req.query;

  if (error) {
    return res.status(400).json({
      authType: String(authType),
      error: String(error),
      errorDescription: String(error_description || ''),
    });
  }

  if (!code || !state) {
    return res.status(400).json({
      authType: String(authType),
      error: 'code and state are required',
    });
  }

  return res.json({
    authType: String(authType),
    code: String(code),
    state: String(state),
  });
});

// 서버 실행
const PORT = process.env.PORT || 3000;
app.listen(PORT, () => {
  console.log(`Server running on port ${PORT}`);
});
