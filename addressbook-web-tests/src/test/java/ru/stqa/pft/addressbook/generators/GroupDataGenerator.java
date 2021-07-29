package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GroupDataGenerator {

    @Parameter(names = "-gc", description = "Group count")
    public int count;

    @Parameter(names = "-gf", description = "Target group file")
    public String file;

    @Parameter(names = "-gd", description = "Data format")
    public String dataFormat;


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
        if (dataFormat.equals("csv")) {
            saveAsCsv(groups, new File(file));
        } else if (dataFormat.equals("xml")) {
            saveAsXml(groups, new File(file));
        } else if (dataFormat.equals("json")) {
            saveAsJson(groups, new File(file));
        } else {
            System.out.println("Unrecognized format " + dataFormat);
        }
    }

    private void saveAsJson(List<GroupData> groups, File file) {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(groups);
        try (Writer writer = new FileWriter(file)) {
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveAsXml(List<GroupData> groups, File file) {
        XStream xStream = new XStream();
        xStream.processAnnotations(GroupData.class);
        String xml = xStream.toXML(groups);
        try (Writer writer = new FileWriter(file)) {
            writer.write(xml);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveAsCsv(List<GroupData> groups, File file) {
        try (Writer writer = new FileWriter(file)) {
            for (GroupData groupData : groups) {
                writer.write(String.format("%s;%s;%s\n", groupData.getName(), groupData.getHeader(), groupData.getFooter()));
            }
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
