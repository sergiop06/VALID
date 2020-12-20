import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-users-list',
  templateUrl: './users-list.component.html',
  styleUrls: ['./users-list.component.css']
})
export class UsersListComponent implements OnInit {

  users: any;
  firstName  = '';
  selectedRowIds: Set<number> = new Set<number>();


  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.getUsers();
  }

  getUsers(): void{
    this.userService.getAll().
      subscribe(
        users => {
          this.users = users;
          console.log(users);
        },
        error => {
          console.log(error);
        }
      );
  }

  refresh(): void {
    this.getUsers();
  }

  onRowClick(id: number) {
    console.log("row click  "+ id);
    if(this.selectedRowIds.has(id)) {
     this.selectedRowIds.delete(id);
    }
    else {
      this.selectedRowIds.add(id);
    }

    console.log("selected rows  == ");
    for(id of this.selectedRowIds){
      console.log(id);
    }
  }

  rowIsSelected(id: number) {
    return this.selectedRowIds.has(id);
  }

  getSelectedRows(){
    return this.users.filter(x => this.selectedRowIds.has(x.id));
  }

  onLogClick() {
    console.log(this.getSelectedRows());
  }

  refreshList(): void {
    this.getUsers();
   this.selectedRowIds.clear();
  }

  processUsers(){
    var ids : number[] = [];
    for(let number of this.selectedRowIds){
     ids.push(number);
    }
    this.userService.process(ids)
    .subscribe(
      response => {
        console.log(response);
        this.refreshList();
      },
      error => {
        console.log(error);
      });
}
}
