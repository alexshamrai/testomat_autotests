# Testomat.io E2E Tests

This project contains an end-to-end (E2E) test suite for [Testomat.io](https://app.testomat.io), serving as a demonstration of various testing tools and libraries in Java. The primary aim is not to achieve exhaustive test coverage or employ advanced test design techniques but to showcase the integration and application of different testing tools for both API and UI testing contexts.

## 🧪 API Tests

API testing is conducted on the public API using [RestAssured](https://rest-assured.io/) integrated with [ExtentReports](https://www.extentreports.com/) for enhanced reporting capabilities. The reports are locally stored in the following directory: `build/extentreports/`

## 🌐 UI Tests

UI testing employs [Selenide](https://selenide.org/) and [Playwright](https://playwright.dev/) in conjunction. The usage of two frameworks concurrently is primarily for learning and exploration purposes.

## 🛠️ Running Tests Locally

To run tests having a user for Testomat.io is required. User credentials should be specified in environment variables:set `USER_EMAIL` and `USER_PASSWORD`.
Before running tests, make sure that environment variables are set in your IDE or via terminal

```sh
export USER_PASSWORD={user_password}
export USER_EMAIL={user_email@gmail.com}
```

Predetermined tasks are utilized for running tests by junit tags. 

1. **Run Smoke Tests:**
   Execute `gradle smokeTests`
2. **Run Api Tests:**
   Execute `gradle apiTests`
3. **Run UI Selenide Tests:**
   Execute `gradle uiSelenideTests`
4. **Run UI Playwright Tests:**
   Execute `gradle uiPlaywrightTests`