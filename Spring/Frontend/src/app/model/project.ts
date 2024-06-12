export class Project {

  public id: number;
  public managerId: number;
  public name: string;
  public description: string;
  public members: string;

  constructor(id: number, projectManagerId: number, name: string, description: string, members: string) {
    this.id = id;
    this.managerId = projectManagerId;
    this.name = name;
    this.description = description;
    this.members = members;
  }

  toString(): string {
    return "Name: " + this.name + " Description: " + this.description + " Members: " + this.members + " Project Manager ID: " + this.managerId;
  }


}
