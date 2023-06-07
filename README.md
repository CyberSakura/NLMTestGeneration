# NLMTestGeneration
This project is used to test the test suite generate ability from Natural Language Model ChatGPT (model v3.5) and Microsoft Bing

## Test Subject
### 1. TicTacToe (The project is from [mister0/TicTacToe](https://github.com/mister0/TicTacToe))
A simple console-running tic-tac-toe game writen by java, and build by maven.


## Test Generation Subject
### 1. [ChatGPT](https://openai.com/blog/chatgpt)
### 2. [Google Bard](https://bard.google.com/)

## Evaluation Result
The Evaluation result is based on two metrics: 
1. Code coverage with [Jacoco](https://www.eclemma.org/jacoco/) (running on Intellij IDE)
2. Mutation testing score within [Pitest](https://pitest.org/) 

Code coverage result is stored in **Code Coverage Result/**

Mutation testing score is stored in **target/pit-reports/** as html file

## Other References
1. Chat history for training test suite generation for [TicTacToe with ChatGPT](https://chat.openai.com/share/ecae0df5-5d0e-42b9-bf17-c87882b1fb25)
2. Unable to export Bard chat history since Bard's history is stored as Bard Activity, which only records the general area from user's device, IP address, or Home or Work addresses in user's Google Account
