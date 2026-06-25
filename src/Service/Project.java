package Service;

public class Project {

    private int projectId;
    private String projectName;
    private int cost;
    private int benefit;

    public Project(int projectId,
                   String projectName,
                   int cost,
                   int benefit) {

        this.projectId = projectId;
        this.projectName = projectName;
        this.cost = cost;
        this.benefit = benefit;
    }

    public int getProjectId() {
        return projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public int getCost() {
        return cost;
    }

    public int getBenefit() {
        return benefit;
    }

    @Override
    public String toString() {

        return projectId +" | " +projectName +" | Cost=" +cost +" | Benefit=" + benefit;
    }
}