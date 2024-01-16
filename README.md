# CLI File Processor

The CLI File Processor is a simple Java-based command-line tool designed to process text files in a given directory. It specifically targets files with a ".in" extension, trims spaces and tabs at the start and end of each line, removes non-alphanumeric characters (except for specified punctuations), and outputs the processed content to new files with a ".out" extension.

## Features

- **Directory Processing**: Processes all ".in" files within a specified directory.
- **Text Trimming**: Trims leading and trailing whitespace (spaces and tabs) from each line.
- **Character Filtering**: Removes non-alphanumeric characters while allowing an extensible set of punctuation marks.
- **Parallel Processing**: Utilizes Java parallel streams for efficient processing on multicore systems.
- **Simple Output**: Writes processed content to new files with a ".out" extension, preserving the original file names.

## Requirements

- Java JDK 11 or later.

## Installation

As this tool is intended to be used as a script, there is no installation process. Ensure you have Java JDK 11 installed on your system.

## Usage

1. **Prepare Your Files**: Place all the ".in" files you want to process into a single directory.

2. **Run the Tool**: Use the following command to run the tool:

   ```sh
   java FileProcessor.java <directory>
   ```

   Replace `<directory>` with the path to the directory containing your ".in" files.

3. **Check Output**: After running the tool, check the same directory for ".out" files containing the processed content.

## Extending Punctuation Set

To extend the set of punctuation characters that are preserved during processing, modify the `PUNCTUATIONS` set in the `FileProcessor.java` file.

Example:

```java
private static final Set<Character> PUNCTUATIONS = new HashSet<>(
    Arrays.asList(',', '.', ';', '?', '!', ':', '-')
);
```

## License

This project is licensed under the Apache License 2.0.
See the LICENSE file for details.
