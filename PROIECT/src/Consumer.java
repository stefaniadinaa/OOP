import com.sun.source.tree.Tree;

import java.util.*;

public abstract class Consumer {
    // lista de cunoscuti
    protected ArrayList<Consumer> acquaintances;
    protected static class Resume {
        Information information;
        TreeSet<Education> education;
        TreeSet<Experience> experience;

        public Resume(ResumeBuilder resume) {
            this.information = resume.information;
            this.education = resume.education;
            this.experience = resume.experience;

            // se arunca ResumeIncompleteException daca unul dintre campuri este gol
            try {
                if (information == null) {
                    throw new ResumeIncompleteException("Mai baga la informatie");
                } else if (education.size() == 0 || education == null) {
                    throw new ResumeIncompleteException("N-ai fost la scoala?");
                } else if (experience.size() == 0 || experience == null) {
                    throw new ResumeIncompleteException("Dar fa si o facultate");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Resume resume = (Resume) o;

            // daca information sunt diferite returnez false
            if (!information.equals(resume.information)) {
                return false;
            }

            // daca colectiile de date despre invatamant sunt diferite, returnez false
            if (!education.equals(resume.education)) {
                return false;
            }

            // daca colectiile de date despre experienta sunt diferie, returnez false
            if (!experience.equals(resume.experience)) {
                return false;
            }

            // sunt egale
            return true;
        }

        // clasa builder pentru obiecte de tip resume
        public static class ResumeBuilder {
            Information information;
            TreeSet<Education> education;
            TreeSet<Experience> experience;

            public ResumeBuilder() {
                this.information = null;
                this.education = null;
                this.experience = null;
            }

            public ResumeBuilder(Information information) {
                this.information = information;
                this.education = new TreeSet<>();
                this.experience = new TreeSet<>();
            }

            public ResumeBuilder education(Education education) {
                this.education.add(education);
                return this;
            }

            public ResumeBuilder experience(Experience experience) {
                this.experience.add(experience);
                return this;
            }

            public Resume build() {
                return new Resume(this);
            }
        }
    }
    protected Resume resume;
    protected Application application;

    public void add(Education education) {
        resume.education.add(education);
    }

    public void add(Experience experience) {
        resume.experience.add(experience);
    }

    public void add(Consumer consumer) {
        acquaintances.add(consumer);
    }

    /*
        Fac BFS si ma opresc cand scot din coada elementul cautat; returnez apoi distanta pana la acesta
     */
    public int getDegreeInFriendship(Consumer consumer) {
        // voi simula comportamentul unei cozi cu LinkedList
        Queue<Consumer> queue = new LinkedList<>();

        // folosesc un hashtable pentru a tine cont de prietenii vizitati si de distanta pana la acestia
        // pentru a folosi hashtable trebuie suprascrie hashCode si equals; mai jos este suprascrierea lor
        Hashtable<Consumer, Integer> visited = new Hashtable<>();

        // cautarea se face pornind de la Consumer-ul actual
        queue.add(this);
        visited.put(this, 0);

        while (!queue.isEmpty()) {
            // extrag urmatoarea cunostinta
            Consumer firstInQueue = queue.poll();

            // returnez distanta daca am gasit omul
            if (firstInQueue.equals(consumer)) {
                return visited.get(consumer);
            }

            for (Consumer acquaintance : firstInQueue.acquaintances) {
                // daca prietenul nu se gaseste in hash-ul de vizitari, atunci ii asociez o distanta si il adaug
                if (visited.get(acquaintance) == null) {
                    visited.put(acquaintance, visited.get(firstInQueue) + 1);
                    queue.add(acquaintance);
                }
                // daca distanta calculata anterior este mai mare decat distanta prin parintele curent,
                // atunci inlocuiesc inregistrarea veche cu perechea nou updatata
                else if (visited.get(acquaintance) > visited.get(firstInQueue) + 1) {
                    // sterg inregistrarea veche
                    visited.remove(acquaintance);
                    // adaug noua inregistrare cu distanta updatata
                    visited.put(acquaintance, visited.get(firstInQueue) + 1);
                    queue.add(acquaintance);
                }
            }
        }

        // daca nu se gaseste cunostinta, returnez -1
        return -1;
    }

    public void remove(Consumer consumer) {
        acquaintances.remove(consumer);
    }

    public Integer getGraduationYear() {
        // returnez prima inregistrare a unui proces de invatamant (ce reprezinta ultimul proces de invatamant urmat)
        return resume.education.first().getEnd().getYear();
    }

    public Double meanGPA() {
        double sum = 0;

        // parcurg intreaga lista de cicluri de invatamnt si adun la sum gpa pe fiecare
        for (Education education : resume.education) {
            sum += education.getGpa();
        }

        sum /= resume.education.size();

        return sum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Consumer consumer = (Consumer) o;

        if (!acquaintances.equals(consumer.acquaintances)) {
            return false;
        }

        if (!resume.equals(consumer.resume)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(acquaintances);
    }

    public ArrayList<Consumer> getAcquaintances() {
        return acquaintances;
    }

    public void setAcquaintances(ArrayList<Consumer> acquaintances) {
        this.acquaintances = acquaintances;
    }

    public Resume getResume() {
        return resume;
    }

    public void setResume(Resume resume) {
        this.resume = resume;
    }
}
