public class DepartmentFactory {
    public Department departmentFactory(String typeOfDepartment) {
        if (typeOfDepartment == null || typeOfDepartment.length() == 0) {
            return null;
        }

        if (typeOfDepartment.equals("IT")) {
            return new IT();
        }

        if (typeOfDepartment.equals("Management")) {
            return new Management();
        }

        if (typeOfDepartment.equals("Marketing")) {
            return new Marketing();
        }

        if (typeOfDepartment.equals("Finance")) {
            return new Finance();
        }

        return null;
    }
}
