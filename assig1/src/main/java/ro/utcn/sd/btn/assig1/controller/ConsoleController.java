package ro.utcn.sd.btn.assig1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ro.utcn.sd.btn.assig1.exception.QuestionNotFoundException;
import ro.utcn.sd.btn.assig1.exception.TagNotFoundException;
import ro.utcn.sd.btn.assig1.exception.UserNotFoundException;
import ro.utcn.sd.btn.assig1.model.Question;
import ro.utcn.sd.btn.assig1.model.Tag;
import ro.utcn.sd.btn.assig1.model.User;
import ro.utcn.sd.btn.assig1.service.QuestionManagementService;
import ro.utcn.sd.btn.assig1.service.TagManagementService;
import ro.utcn.sd.btn.assig1.service.UserManagementService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;

@Component
@RequiredArgsConstructor
public class ConsoleController implements CommandLineRunner {
    private final Scanner scanner = new Scanner(System.in);
    private final InputStreamReader isr=new InputStreamReader(System.in);
    private final BufferedReader br= new BufferedReader(isr);

    private final QuestionManagementService qManagementService;
    private final UserManagementService uManagementService;
    private final TagManagementService tManagementService;

    private User user = null;

    @Override
    public void run(String... args) {
        print("Welcome!");
        boolean done = false;
        while (!done) {
            print("Enter a command: ");
            String command = scanner.next().trim();
                try {
                    done = handleCommand(command);
                } catch (UserNotFoundException userNotFoundException) {
                    print("The user with the given credentials was not found!");
                } catch (QuestionNotFoundException questionNotFoundException) {
                    print("The question was not found!");
                } catch (TagNotFoundException tagNotFoundException) {
                    print("The tag was not found!");
                }
        }
    }

    private boolean handleCommand(String command) {
        switch (command) {
            case "add_question":
                handleAdd();
                return false;
            case "filter_question":
                handleFilter();
                return false;
            case "list_questions":
                handleList();
                return false;
            case "add_user":
                handleAddUser();
                return false;
            case "login":
                handleLogin();
                return false;
            case "exit":
                return true;
            default:
                print("Unknown command. Try again.");
                return false;
        }
    }

    private void handleAdd() {

        if(user==null) {
            print("You need to be logged in!");
        } else {

            print("title:");
            String title = scanner.next().trim();

            print("tag name:");
            String name = scanner.next().trim();
            Tag tag = tManagementService.findTag(name);

            print("text:");
            String text = null;
            try {
                text = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Timestamp time = new Timestamp(System.currentTimeMillis());

            Question question = qManagementService.askQuestion(this.user, time, title, tag, text);
            print("Created question: " + question + ".");
        }
    }

    private void handleFilter() {
        print("title of the question you are searching:");
        String title = scanner.next().trim();

        List<Question> question = qManagementService.findQuestionByTitle(title);
        qManagementService.findQuestionByTitle(title).forEach(s -> print(s.toString()));
        //print("Question " + question + " with title " + title + " was found.");
    }

    private void handleList() {
        qManagementService.listQuestions().forEach(s -> print(s.toString()));
    }

    private void handleAddUser(){
        print("username:");
        String userName= scanner.next().trim();
        print("password:");
        String password = scanner.next().trim();

        User user1 = uManagementService.addUser(userName, password);
        print("Created user: " + user1 + ".");
    }

    private void handleLogin(){
        print("username:");
        String userName= scanner.next().trim();
        print("password:");
        String password = scanner.next().trim();

        user = uManagementService.findUser(userName, password);
        print("Login completed");
    }

    private void print(String value) {
        System.out.println(value);
    }

}
