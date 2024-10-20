# Algeb
### Algebra Solver Android App

An Android application that provides tools for solving matrices, performing basic operations with complex numbers, and solving systems of equations.

![Screenshots of Algeb](screenshot.png)

## Features

- **Matrix Operations**: Solve and manipulate matrices of various sizes, including calculating inverses, determinants, and transposes.
- **Complex Number Operations**: Perform basic arithmetic operations with complex numbers, such as addition, subtraction, multiplication, and division.
- **Equation System Solver**: Solve systems of linear equations using methods like Cramer's Rule.

## Installation

### Prerequisites

- Android device running Android version 5.0 (Lollipop) or higher.
- [Android Studio](https://developer.android.com/studio) installed on your computer (if you wish to build from source).

### Install from APK

1. Download the latest APK release from the [Releases](#) section.
2. Transfer the APK file to your Android device.
3. On your device, navigate to **Settings > Security** and enable **Unknown Sources** to allow installation of apps from sources other than the Google Play Store.
4. Open the APK file on your device and follow the on-screen instructions to install the app.

### Build from Source

1. Clone the repository:

   ```bash
   git clone https://github.com/VicentCodes/Algeb.git
   ```

2. Open the project in Android Studio.
3. Connect your Android device to your computer or set up an emulator.
4. Click **Run** to build and install the app on your device.

## Usage

### Matrix Operations

1. Open the app and select **Matrix Solver** from the main menu.
2. Enter the order (size) of the matrix.
3. Input the elements of the matrix by filling in the prompted positions.
4. Choose the desired operation:
   - **Inverse**: Calculate the inverse of the matrix.
   - **Determinant**: Compute the determinant.
   - **Transpose**: Get the transpose of the matrix.
5. Tap **Calculate** to see the result displayed.

### Complex Number Operations

1. Select **Complex Number Calculator** from the main menu.
2. Input the real and imaginary parts of the first complex number.
3. Input the real and imaginary parts of the second complex number.
4. Choose the operation:
   - **Addition (+)**
   - **Subtraction (-)**
   - **Multiplication (×)**
   - **Division (÷)**
5. Tap **Calculate** to view the result in standard form.

### Equation System Solver

1. Choose **Equation Solver** from the main menu.
2. Input the coefficients for each variable in the equations.
3. Select the solving method (e.g., Cramer's Rule).
4. Tap **Solve** to get the solutions for the variables.


## Contributing

We welcome contributions from the community. If you'd like to contribute:

1. Fork the repository.
2. Create a new branch for your feature or bug fix.
3. Commit your changes and push the branch to your forked repository.
4. Open a pull request with a detailed description of your changes.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Contact

For any questions or feedback, please open an issue or contact us at [contact@VicentCodes.com](mailto:contact@VicentCodes.com).

---

**Note:** The code snippets provided in the repository are written in Kotlin and Java, utilizing Android's development framework. The app includes classes and methods for performing mathematical computations, such as matrix inversion and complex number operations, ensuring accurate and efficient calculations.
