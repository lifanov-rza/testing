package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

    @Parameter(names = "-cc", description = "Contact count")
    public int count;

    @Parameter(names = "-cf", description = "Target contact file")
    public String file;

    @Parameter(names = "-cd", description = "Data format")
    public String dataFormat;

    public static void main(String[] args) {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander commander = new JCommander(generator);
        try {
            commander.parse(args);
        } catch (ParameterException pex) {
            commander.usage();
            return;
        }
        generator.run();
    }

    private void run() {
        List<ContactData> contacts = generateContacts(count);
        if (dataFormat.equals("json")) {
            saveAsJson(contacts, new File(file));
        } else {
            System.out.println("Unrecognized format " + dataFormat);
        }
    }

    private void saveAsJson(List<ContactData> contacts, File file) {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(contacts);
        try (Writer writer = new FileWriter(file)) {
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<>();
        File photo = new File("src/test/resources/zel.jpg");
        for (int i = 0; i < count; i++) {
            contacts.add(new ContactData().withFirstname((String.format("firstname %s", i)))
                    .withLastname(String.format("Lastname %s", i))
                    .withAddress((String.format("Moscow, Arbat, home %s", i)))
                    .withHomePhone(String.valueOf(111 + i))
                    .withEmail(String.format("abra%s@mail.ru", i))
                    .withGroupname("Test 1").withPhoto(photo));
        }
        return contacts;
    }
}
