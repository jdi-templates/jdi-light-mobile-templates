#### JDI Mobile documentation
See here: https://jdi-docs.github.io/jdi-mobile/
# JDI Mobile Example

## How to launch web tests on Android emulator  

Stable environment: Java azul-13.0.13, Maven 3.9.5, JDI-Light 1.3.11, TestNG 7.4.0, Aspectj 1.9.*, Appium 1.20.2, Android 10 <br/>

1. Launch Android emulator and wait until home screen is ready.
1. Launch Appium and find out the listening URL in the console (usually http://0.0.0.0:4723)
1. Set following settings in **test/resources/test.properties** file:
```
   driver=android 
   remote.type=appium
# should be URL form prev. step and remove leading # if any
   driver.remote.url=http://0.0.0.0:4724/wd/hub 
```
4. Set following settings in **test/resources/android.properties** file
```
# Section 'There we can find Device UID and name' and remove leading # if any
   deviceName={GET_UID_FROM_YOUR_ANDROID_VIRTUAL_DEVICE} 
```


More information about JDI-Light in [Documentation](https://jdi-docs.github.io/jdi-light/?java#introduction)
   
## External tools installation (for novice)

### Android emulator installation
The easiest way to use Android Emulator is via Android Studio, but it's also possible to install Android SDK only (https://developer.android.com/studio/releases/sdk-tools)
We describe a way via Android Studio.

1. Download and install Android Studio (https://developer.android.com/studio). Note: in case of error with HAXM installation just ignore it.
1. Run Android Studio
1. Run AVD Manager (Configure -> AVD Manager) (or via Tools -> AVD Manager for an open project)
1. Create a virtual device with any available parameters (for example, Pixel_3a_API_30_x86)
1. Run virtual device via button in Actions column ![Virtual device run](./docs/run_and_power_up.png "Run device")
1. Power up your device via Power button (is highlighted on a previous screen)
1. Open Chrome, confirm all agreements to get empty Chrome window with search field
1. Open About Chrome  and find Chrome version (chromedriver and Chrome version should be equal, so copy it. Needed after Appium installation)<br/>
   ![Ready Chrome](./docs/empty_chrome.png "Empty Chrome")
   
Ready!

### Install Appium

1. Install Appium 
   * For Windows usually we need to install **npm** (https://nodejs.org/en/) and run `npm install -g appium`
   * All information is on official site https://appium.io/docs/en/about-appium/getting-started/?lang=en
   * After installation make sure that you can run appium in terminal:
     * (for Windows) add appium directory to PATH environment variable. If you used 'npm install ...' command for installation appium will be in C:\Users\\{USER_NAME}\AppData\Roaming\npm 
     * (for Windows) if you see error message like 'File cannot be loaded because the execution of scripts is disabled on this system. Please see "get-help about_signing" for more details' open new PowerShell terminal 'as Administrator' and run this command `Set-ExecutionPolicy -ExecutionPolicy Unrestricted`
1. Download chromedriver for Chrome with version equal to Chrome version on your device (The number before the first '.' should be equal!). More information https://github.com/appium/appium/blob/master/docs/en/writing-running-appium/web/chromedriver.md
   * Download correct version from https://chromedriver.chromium.org/downloads 
   * Put it to npm modules. For Windows example path is: `c:\Users\{USER_NAME}\AppData\Roaming\npm\node_modules\appium\node_modules\appium-chromedriver\chromedriver\win\`
1. (For Windows) Several environment variables are needed to work with emulator (**!Note** Restart Appium after this step if it's running (Ctrl+C can be used to stop Appium)):
   * JAVA_HOME - path to JDK installation folder (NOT bin folder inside!). Example 'c:\Program Files\Java\jdk1.8.0_281\'   
   * ANDROID_HOME - path to Android SDK, can be found in Android Studio in Menu `File -> Settings... -> Android SDK`. Example `c:\Users\{USER_NAME}\AppData\Local\Android\Sdk\`
   * ANDROID_SDK_ROOT - the same value as ANDROID_HOME
   * ANDROID_TOOLS - ANDROID_HOME\tools <br/>
   ![Android SDK](./docs/sdk_path.png "Android SDK path location")
     
     
### There we can find Device UID and name

1. Device name is visible in Android Virtual Device Manager in a list
1. UID is available in Extended controls (...) -> Help -> About
![Device UID and name](./docs/android_name_uid.png "Device UID and name")


### Run Android native app tests tests
1. Launch the created virtual device
1. Run 'appium' in console
1. Open the tests project in IDEA. (Optional)
1. Run the following maven command
```
mvn clean test
```
After test run finished run
```
mvn allure:serve
```

### Helpfull notes
1. on MacOS Appium 2 needs to be run in following regime
```
appium --base-path=/wd/hub  
```
2. pay attention to a terminal where appium is running - there you can see logs and what is going on
3. see the **src/test/resources/android.properties** to put some properties (might be different according to your environment)
   1. app= - **Absolute** path to your application under test. (example to run simple tests is here: **src/main/resources/ApiDemos-debug.apk** )
   2. automationName= - name of a testing framework you using(eg. UiAutomator2, Espresso. Appium 2 runs with UiAutomator2)
4. It is necessary to check if this environment variables a set: JAVA_HOME , ANDROID_HOME, ANDROID_SDK_ROOT, ANDROID_TOOLS (as it is written above)
5. To run tests using Maven, you need to make sure that the Java version in the JAVA_PATH environment variable and the Java version in the project match.
6. when you see comment above marked (for Windows) - for other environments you need to do the same but some other way.
