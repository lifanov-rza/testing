package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

    private Contacts contactCache = null;

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("middlename"), contactData.getMiddlename());
        type(By.name("lastname"), contactData.getLastname());
        attach(By.name("photo"), contactData.getPhoto());
        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroupname());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void submitContactCreation() {
        click(By.name("submit"));
    }

    public void returnToHomePage() {
        click(By.linkText("home page"));
    }

    public void initContactModification(int id) {
        click(By.xpath(String.format("//a[@href='edit.php?id=%s']", id)));
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public void select(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void selectById(int id) {
        wd.findElement(By.xpath(String.format("//input[@value='%s']", id))).click();
    }

    public void deleteSelectedContacts() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void confirmDeletion() {
        wd.switchTo().alert().accept();
    }

    public boolean isContactPresent() {
        return isElementPresent(By.name("selected[]"));
    }

    public void create(ContactData contactData, boolean creation) {
        fillContactForm(contactData, creation);
        submitContactCreation();
        contactCache = null;
        returnToHomePage();
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public void modify(ContactData contact) {
        initContactModification(contact.getId());
        fillContactForm(contact, false);
        submitContactModification();
        contactCache = null;
        returnToHomePage();
    }

    public void delete(ContactData deletedContact) {
        selectById(deletedContact.getId());
        deleteSelectedContacts();
        confirmDeletion();
        contactCache = null;
    }

    public List<ContactData> list() {
        List<ContactData> contacts = new ArrayList<>();
        List<WebElement> elements = wd.findElements(By.xpath("//tr[@name='entry']"));
        for (WebElement element : elements) {
            String lastname = element.findElement(By.xpath("//td[2]")).getText();
            String firstname = element.findElement(By.xpath("//td[3]")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            ContactData contact = new ContactData().withId(id).withFirstname(firstname).withLastname(lastname);
            contacts.add(contact);
        }
        return contacts;
    }

    public Contacts all() {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> rows = wd.findElements(By.xpath("//tr[@name='entry']"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            String lastname = cells.get(1).getText();
            String firstname = cells.get(2).getText();
            String phones = cells.get(5).getText().replaceAll("\n+", "");
            String address = cells.get(3).getText();
            String emails = cells.get(4).getText().replaceAll("\n+", "");
            int id = Integer.parseInt(row.findElement(By.tagName("input")).getAttribute("value"));
            ContactData contact = new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
                    .withAllPhones(phones).withAddress(address).withEmails(emails);
            contactCache.add(contact);
        }
        return new Contacts(contactCache);
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModification(contact.getId());
        String firstname = wd.findElement(By.xpath("//input[@name='firstname']")).getAttribute("value");
        String lastname = wd.findElement(By.xpath("//input[@name='lastname']")).getAttribute("value");
        String address = wd.findElement(By.xpath("//textarea[@name='address']")).getText();
        String home = wd.findElement(By.xpath("//input[@name='home']")).getAttribute("value");
        String mobile = wd.findElement(By.xpath("//input[@name='mobile']")).getAttribute("value");
        String work = wd.findElement(By.xpath("//input[@name='work']")).getAttribute("value");
        String email = wd.findElement(By.xpath("//input[@name='email']")).getAttribute("value");
        String email2 = wd.findElement(By.xpath("//input[@name='email2']")).getAttribute("value");
        String email3 = wd.findElement(By.xpath("//input[@name='email3']")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withFirstname(firstname).withLastname(lastname).withAddress(address)
                .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work).withEmail(email).withEmail2(email2)
                .withEmail3(email3);
    }
}

