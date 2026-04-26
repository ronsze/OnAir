const express = require('express');
const fetch = require('node-fetch'); // npm install node-fetch
const admin = require('firebase-admin');
const cors = require('cors');
const app = express();

app.use(cors());
app.use(express.json());

// 로그인 리다이렉트 페이지
app.get('/login/redirect', (req, res) => {
  const { code, state, error, error_description } = req.query;

  if (error) {
    return res.status(400).send(`
      <html>
        <head><meta charset="utf-8" /></head>
        <body>
          <h2>로그인 실패</h2>
          <p>${error}</p>
          <p>${error_description || ''}</p>
        </body>
      </html>
    `);
  }

  return res.send(`
    <html>
      <head><meta charset="utf-8" /></head>
      <body>
        <h2>로그인 리다이렉트 완료</h2>
        <p>code: ${code || ''}</p>
        <p>state: ${state || ''}</p>
      </body>
    </html>
  `);
});

// 서버 실행
const PORT = process.env.PORT || 3000;
app.listen(PORT, () => {
  console.log(`Server running on port ${PORT}`);
});
