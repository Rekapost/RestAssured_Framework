# RestAssured API Testing Framework 🚀

## 📌 Overview
This **RestAssured API Testing Framework** is a comprehensive, scalable, and efficient test automation solution for **RESTful APIs**. Built using **REST Assured** and **TestNG**, it follows **BDD principles**, supports **data-driven testing**, integrates seamlessly with **CI/CD pipelines**, and provides detailed test execution reports.

## 🛠️ Tech Stack
- **Programming Language:** Java
- **API Testing Tools:** REST Assured, Postman
- **Framework Design:** BDD with Cucumber, TestNG
- **Build & Dependency Management:** Maven
- **CI/CD:** Jenkins, GitHub Actions
- **Reporting Tools:** Allure Reports, Extent Reports
- **Logging:** Log4j
- **Version Control:** Git, GitHub

## 🎯 Key Features
✅ **REST Assured API Automation** - Automates API testing efficiently with Java.  
✅ **BDD with Cucumber** - Uses **Gherkin syntax** for human-readable test cases.  
✅ **Data-Driven Testing** - Fetches test data from external sources (**JSON, Excel, DB**).  
✅ **Custom Reporting** - Generates **Allure & Extent Reports** for detailed execution insights.  
✅ **CI/CD Ready** - Seamlessly integrates with **Jenkins & GitHub Actions** for automated test execution.  
✅ **Logging & Debugging** - Implements **Log4j** for improved debugging and tracking.  
✅ **Parallel Execution Support** - Runs multiple API test scenarios simultaneously to reduce execution time.  
✅ **Dynamic Request & Response Validation** - Verifies API responses dynamically using schema validation.  

## 🏗️ Project Structure
```
├── src
│   ├── main
│   │   ├── java (Core Framework Code)
│   │   ├── resources (Config files, API Endpoints, Test Data)
│   ├── test
│   │   ├── java (Test Cases)
│   ├── reports (Test Execution Reports)
│   ├── pom.xml (Maven dependencies)
│   ├── README.md
```

## 🚀 Getting Started
### 1️⃣ Clone the Repository
```bash
git clone https://github.com/Rekapost/RestAssured_Framework.git
cd RestAssured_Framework
```
### 2️⃣ Install Dependencies
Ensure **Maven** is installed, then run:
```bash
mvn clean install
```
### 3️⃣ Run Tests
Execute all API test cases:
```bash
mvn test
```
Run specific API test suites using **TestNG XML**:
```bash
mvn test -DsuiteXmlFile=src/test/resources/api-testng.xml
```
### 4️⃣ View Reports
- **Extent Reports:** `target/reports/ExtentReport.html`
- **Allure Reports:** Generate and open with:
  ```bash
  mvn allure:serve
  ```

## 🔄 CI/CD Integration
- **Jenkins Pipeline:** Configure `Jenkinsfile` for automated API test execution.
- **GitHub Actions:** Add a `.github/workflows/test.yml` file for continuous testing automation.

## 📌 Best Practices
- Follow **TestNG annotations** to structure test cases effectively.
- Use **parameterization** to execute tests with multiple datasets.
- Implement **schema validation** to ensure API response consistency.
- Integrate with **Docker & Kubernetes** for containerized test execution.

## 🤝 Contributing
We welcome contributions! Feel free to **fork, create issues, and submit PRs**.

## 📞 Contact
📧 Email: rekaharisri@gmail.com  
🔗 LinkedIn: [linkedin.com/in/rekasrimurugan](#)  
🌐 GitHub: [github.com/Rekapost](https://github.com/Rekapost)  

---
🌟 *If you find this repository useful, please ⭐ it!*

