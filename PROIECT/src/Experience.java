import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Objects;

public class Experience implements Comparable<Experience> {
    private LocalDate start;
    private LocalDate end;
    private String position;
    private Company company;

    public Experience(LocalDate start, LocalDate end, String position, Company company) {
        // arunca exceptie de tipul DateTimeException cu mesajul "Date invalide"
        // in cazul in care data de start nu este mai mica decat data de final
        // Observatie: nu am gasit InvalidDatesExpression, probabil ca v-ati referit la DateTimeException
        try {
            if (end != null) {
                if (start.compareTo(end) != -1) {
                    throw new DateTimeException("Date invalide");
                }
            }
        } catch (DateTimeException e) {
            System.out.println(e.getMessage());
        }

        this.start = start;
        this.end = end;
        this.position = position;
        this.company = company;
    }

    @Override
    public int compareTo(Experience experience) {
        // ordonare in ordine crescatoare dupa numele companiei
        // daca una dintre datele de final sunt null sau daca sunt egale
        if (end == null || experience.end == null || end.equals(experience.end)) {
            return getCompany().getName().compareTo(experience.getCompany().getName());
        }

        // ordonare descrecatoare dupa data de sfarsit
        return -end.compareTo(experience.end);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Experience that = (Experience) o;
        return Objects.equals(start, that.start) &&
                Objects.equals(end, that.end) &&
                Objects.equals(position, that.position) &&
                Objects.equals(company, that.company);
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public String getPosition() {
        return position;
    }

    public Company getCompany() {
        return company;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
