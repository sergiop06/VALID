import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-user-create',
  templateUrl: './user-create.component.html',
  styleUrls: ['./user-create.component.css']
})
export class UserCreateComponent implements OnInit {

  user = {
    firstName: '',
    lastName: '',
    procesado: false
  }
  submitted = false;

  constructor(private userService: UserService) { }

  ngOnInit(): void {
  }

  createUser(): void {
    const data = {
      firstName: this.user.firstName,
      lastName: this.user.lastName
    };
    console.log(this.user.firstName);
    console.log(this.user.lastName);

    this.userService.create(data)
      .subscribe(
        response => {
          console.log(response);
          this.submitted = true;
        },
        error => {
          console.log(error);
        });
  }

  newUser(): void {
    this.submitted = false;
    this.user = {
      firstName: '',
      lastName: '',
      procesado: false
    };
  }


}
