# LinkedIn Profile Scraper

This application acts as a middle layer between users and LinkedIn's Voyager API, providing user profile data in a more
readable format. Users simply need to input their `li_at` cookie to access their data.

---

## 🚨 DISCLAIMER

- This app is an experimental tool and does **not** use LinkedIn's officially exposed APIs.
- The `li_at` cookie is sensitive data that allows the application to function. **Do not share your cookie with anyone.
  **

---

## 📋 Features

### **Current Available Features**

- **LinkedIn Profile Jobs Scraper**: Fetches and displays your saved jobs in a user-friendly format.
  Choose from the options of SAVED/ IN_PROGRESS / APPLIED / ARCHIVED jobs to fetch data.
---

## 🛠️ Steps to Obtain the `li_at` Cookie

1. Open Google Chrome and go to [LinkedIn](https://linkedin.com). Log in to your account.
2. Press `F12` to open Developer Tools and navigate to the **Network** tab.
3. Click on your profile and locate any network requests containing the URL segment `*/voyager/api/*`.
4. Select the request and check the **Request Headers** section.
5. Locate the `Cookie` string and copy the value of `li_at`. It will look like this:

li_at=AQEDASW23xIFbeqmAAABlJ-vdmcAAAGUw7v6Z04ASs4-RGsBZModgaeaN0zfogdzFFRtCoGMGThhoiVa_lIQSabVXGnCpum9sbipLhNrzDv74CVWdNX-i3uPQIMco00VdlbwN5q2HCYlWvpbM78K5sLQ;

---

## 🚀 How to Run the Application

1. Download the source code.
2. Run the Spring Boot service by starting the `LiProfileScraperApplication.java` file.
3. Open your browser and navigate
   to: [http://localhost:8080/swagger-ui/index.html#/](http://localhost:8080/swagger-ui/index.html#/).
4. Use the available endpoints by providing your `li_at` cookie in the designated input field.

---

## ⚙️ Prerequisites

- Integrated development environment
- JDK 17
- A valid `li_at` cookie obtained from LinkedIn.

---

## ⚠️ Legal and Ethical Use

This application is provided for **personal and experimental purposes only**. It is not affiliated with or endorsed by
LinkedIn. Use it responsibly and ensure compliance with LinkedIn's terms of service.
