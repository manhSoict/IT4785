# 🏡 Hệ thống thuê nhà ngắn hạn

Dự án bao gồm hai phần chính:
- **Backend**: Xây dựng bằng Node.js
- **Frontend**: Giao diện ReactJS
- **Cơ sở dữ liệu**: MySQL (qua XAMPP, cổng mặc định `3306`)

---

## ⚙️ Yêu cầu hệ thống

Trước khi bắt đầu, bạn cần cài sẵn các công cụ sau:

- ✅ [Node.js](https://nodejs.org/)
- ✅ [npm](https://www.npmjs.com/)
- ✅ [ReactJS](https://react.dev/)
- ✅ [XAMPP](https://www.apachefriends.org/index.html) (để chạy MySQL server qua phpMyAdmin)

---

## 🛠️ Thiết lập và chạy hệ thống

### 1. Khởi động MySQL Server

- Mở **XAMPP**, bật **MySQL** (port mặc định: `3306`)
- Tạo CSDL mới tên: `hust_domus` (hoặc tên tương ứng như trong `.env`)
- Import file SQL: `database/DATN_20215420_EL.sql` vào database bạn vừa tạo.

---

### 2. Chạy backend (Node.js)

```bash
cd backend
npm install
npm start
```
---
### 2. Chạy frontend (ReactJS)

```bash
cd frontend
npm install
npm start
```
---
Tác giả
Nguyễn Đức Mạnh - 20215420
Hệ thống thuê nhà ngắn hạn
---