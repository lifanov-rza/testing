package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GroupDataGenerator {

    @Parameter(names = "-c", description = "Group count")
    public int count;

    @Parameter(names = "-f", description = "Target group file")
    public String file;


    public static void main(String[] args) {
        GroupDataGenerator generator = new GroupDataGenerator();
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
        List<GroupData> groups = generateGroups(count);
        save(groups, new File(file));
    }

    private void save(List<GroupData> groups, File file) {
        try {
            Writer writer = new FileWriter(file);
            for (GroupData groupData : groups) {
                writer.write(String.format("%s;%s;%s\n", groupData.getName(), groupData.getHeader(), groupData.getFooter()));
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<GroupData> generateGroups(int count) {
        List<GroupData> groups = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            groups.add(new GroupData().withName(String.format("Test %s", i))
                    .withFooter(String.format("Footer %s", i)).withHeader(String.format("Header %s", i)));
        }
        return groups;
    }
}
