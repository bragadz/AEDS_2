import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Personagem {
    private String id;
    private String name;
    private List<String> alternateNames;
    private String house;
    private String ancestry;
    private String species;
    private String patronus;
    private boolean hogwartsStaff;
    private String hogwartsStudent;
    private String actorName;
    private boolean alive;
    private Date dateOfBirth;
    private int yearOfBirth;
    private String eyeColour;
    private String gender;
    private String hairColour;
    private boolean wizard;

    public Personagem() {
        this.alternateNames = new ArrayList<>();
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAlternateNames(List<String> alternateNames) {
        this.alternateNames = alternateNames;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public void setAncestry(String ancestry) {
        this.ancestry = ancestry;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public void setPatronus(String patronus) {
        this.patronus = patronus;
    }

    public void setHogwartsStaff(boolean hogwartsStaff) {
        this.hogwartsStaff = hogwartsStaff;
    }

    public void setHogwartsStudent(String hogwartsStudent) {
        this.hogwartsStudent = hogwartsStudent;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public void setEyeColour(String eyeColour) {
        this.eyeColour = eyeColour;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setHairColour(String hairColour) {
        this.hairColour = hairColour;
    }

    public void setWizard(boolean wizard) {
        this.wizard = wizard;
    }

    public void imprimir() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = (dateOfBirth != null) ? dateFormat.format(dateOfBirth) : "";

        String alternateNamesString = alternateNames.isEmpty() ? "" : "{" + String.join(", ", alternateNames) + "}";
        String hogwartsStudentString = (hogwartsStudent != null && !hogwartsStudent.isEmpty()) ? hogwartsStudent : "";
        String actorNameString = (actorName != null && !actorName.isEmpty()) ? actorName : "";

        System.out.println(String.format("[%s ## %s ## %s ## %s ## %s ## %s ## %s ## %b ## %s ## %s ## %b ## %s ## %d ## %s ## %s ## %s ## %b]",
                id, name, alternateNamesString, house, ancestry, species, patronus, hogwartsStaff, hogwartsStudentString,
                actorNameString, alive, formattedDate, yearOfBirth, eyeColour, gender, hairColour, wizard));
    }

    public void ler() {
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNext()) id = scanner.next();
        if (scanner.hasNextLine()) name = scanner.nextLine();
        if (scanner.hasNextLine()) {
            String alternateNamesString = scanner.nextLine().trim();
            if (!alternateNamesString.isEmpty() && !alternateNamesString.equals("{}")) {
                String[] alternateNamesArray = alternateNamesString.substring(1, alternateNamesString.length() - 1).split(",");
                for (String alternateName : alternateNamesArray) {
                    alternateNames.add(alternateName.trim());
                }
            }
        }
        if (scanner.hasNextLine()) house = scanner.nextLine();
        if (scanner.hasNextLine()) ancestry = scanner.nextLine();
        if (scanner.hasNextLine()) species = scanner.nextLine();
        if (scanner.hasNextLine()) patronus = scanner.nextLine();
        if (scanner.hasNextBoolean()) hogwartsStaff = scanner.nextBoolean();
        if (scanner.hasNextLine()) hogwartsStudent = scanner.nextLine();
        if (scanner.hasNextLine()) actorName = scanner.nextLine();
        if (scanner.hasNextBoolean()) alive = scanner.nextBoolean();
        if (scanner.hasNextLine()) {
            String dateOfBirthString = scanner.nextLine();
            if (!dateOfBirthString.isEmpty()) {
                try {
                    dateOfBirth = new SimpleDateFormat("dd-MM-yyyy").parse(dateOfBirthString);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        if (scanner.hasNextInt()) yearOfBirth = scanner.nextInt();
        if (scanner.hasNextLine()) eyeColour = scanner.nextLine();
        if (scanner.hasNextLine()) gender = scanner.nextLine();
        if (scanner.hasNextLine()) hairColour = scanner.nextLine();
        if (scanner.hasNextBoolean()) wizard = scanner.nextBoolean();

        scanner.close();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Personagem> personagens = new ArrayList<>();

        while (true) {
            if (!scanner.hasNext()) break;
            String id = scanner.next();
            if (id.equals("FIM")) break;

            Personagem personagem = new Personagem();
            personagem.setId(id);
            personagem.ler();
            personagens.add(personagem);
        }

        for (Personagem personagem : personagens) {
            personagem.imprimir();
        }
        scanner.close();
    }
}
