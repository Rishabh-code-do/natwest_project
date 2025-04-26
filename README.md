# natwest_project

Hit Enpoint [http://127.0.0.1:8080/api/reports/generate](http://127.0.0.1:8080/api/reports/generate) with 'POST' method to generate reports.

# User Feedback System

A full-stack application to collect and display user feedback using **Node.js (Express.js)** for the backend and **React.js** for the frontend.

---

## Table of Contents

- [Getting Started](#getting-started)
- [Backend Setup](#backend-setup)
- [Backend API Endpoints](#backend-api-endpoints)
- [Frontend Setup](#frontend-setup)
- [Frontend Routes](#frontend-routes)
- [Notes](#notes)
- [Technologies Used](#technologies-used)
- [Author](#author)

---

## Getting Started

Follow these steps to set up the project locally.

### Clone the Repository

```bash
git clone https://github.com/your-username/your-repo-name.git
cd your-repo-name
```

# Backend Setup

Follow these steps to set up and run the backend server.

## Steps:

1. Navigate to the backend directory:

```bash
cd backend
```

2. Install backend dependencies:

```bash
npm install
```

3. Run the code

```bash
node server.js
```

# Frontend Setup

Follow these steps to set up and run the frontend.

## Steps:

1. Navigate to the backend directory:

```bash
cd frontend
```

2. Install backend dependencies:

```bash
npm install
```

3. Run the code

```bash
npm start
```


## Backend API Endpoints

### 1. `POST /api/feedback`
Submit user feedback.

#### Request Body

```json
{
  "name": "John Doe",
  "email": "john@example.com",
  "category": "bug",
  "message": "This is amazing!"
}
```

### 2. `GET /api/feedback`
Fetch all feedback entries.

#### Query Parameters (optional)

- `sortField`: Sort feedback by fields like `timestamp` or `name`.
- `category`: Filter on basis of category(feature,bug,suggestion).
- `sortOrder`: Sort by order `asc` or `desc`
- `page`: Page Number
- `limit`: Page Limit

#### Example Request

```bash
GET /api/feedback?category=bug&sortField=date&sortOrder=asc&page=1&limit=10
```








