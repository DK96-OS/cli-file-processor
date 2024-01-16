/*
 * Copyright 2024 DK96-OS
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

public class FileProcessor {

    private static final String FILE_EXTENSION = ".in";
	
    private static final Set<Character> PUNCTUATIONS = new HashSet<>(
        Arrays.asList(',', '.', ';', '?', '!', ':')
    );

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("Usage: java FileProcessor <directory>");
            System.exit(1);
        }

        Path dir = Paths.get(args[0]);
        try (var paths = Files.list(dir)) {
            paths.filter(Files::isRegularFile)
                 .filter(path -> path.toString().endsWith(FILE_EXTENSION))
                 .parallel() // This enables parallel processing of files
                 .forEach(FileProcessor::processFile);
        }
    }

    private static void processFile(Path filePath) {
        try {
            var lines = Files.readAllLines(filePath);
			// Use parallel streams for processing lines
            var processedLines = lines.parallelStream()
                                      .map(FileProcessor::processLine)
                                      .collect(Collectors.toList());
            // Create a new file path with "out" extension
            String outputFileName = filePath.toString()
											.replaceAll("\\.in$", ".out");
            Path outputPath = Paths.get(outputFileName);
            // Write the processed lines to the new file
            Files.write(outputPath, processedLines);
        } catch (IOException e) {
            System.err.println("Error processing file: " + filePath);
        }
    }

    private static String processLine(String line) {
        line = line.trim(); // Trim the line
        StringBuilder cleanLine = new StringBuilder();
        for (char c : line.toCharArray()) {
            if (Character.isLetterOrDigit(c) ||
            	PUNCTUATIONS.contains(c) ||
            	Character.isWhitespace(c)
            ) {
                cleanLine.append(c);
            }
        }
        return cleanLine.toString();
    }
	
}
