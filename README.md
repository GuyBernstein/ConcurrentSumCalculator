# Concurrent Sum Calculator

The Concurrent Sum Calculator is a Java program that demonstrates the use of multithreading to calculate the sum of an array of numbers concurrently. The program allows you to specify the number of elements in the array and the number of processes (threads) to use for the calculation.

## Features

- Generates an array of random numbers based on user input
- Utilizes multiple threads to calculate the sum concurrently
- Implements thread synchronization using a `ReentrantLock` to ensure thread safety
- Provides a user-friendly console interface for input and output

## Usage

1. Clone the repository:
git clone https://github.com/your-username/concurrent-sum-calculator.git

2. Navigate to the project directory:
cd concurrent-sum-calculator

3. Compile the Java files:
javac Main.java

4. Run the program:
java Main

5. Follow the prompts to enter the number of elements in the array and the number of processes to use for the calculation.

6. The program will generate an array of random numbers, divide the work among the specified number of threads, and calculate the sum concurrently.

7. The final sum will be displayed along with the intermediate steps performed by each thread.

## Code Structure

The project consists of the following Java files:

- `Main.java`: Contains the main method and handles user input and output.
- `NumberPool.java`: Represents the pool of numbers and provides methods for adding and retrieving numbers.
- `SumCalculatorThread.java`: Implements the Runnable interface and performs the sum calculation concurrently.

## Contributing

Contributions are welcome! If you find any issues or have suggestions for improvement, please open an issue or submit a pull request.
