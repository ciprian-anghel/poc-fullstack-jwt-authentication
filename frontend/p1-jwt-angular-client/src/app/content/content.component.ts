import { Component } from '@angular/core';
import { LoginService } from '../services/login.service';
import { Login } from '../interfaces/Login';
import { Subscription } from 'rxjs/internal/Subscription';
import { Register } from '../interfaces/Register';

@Component({
  selector: 'app-content',
  templateUrl: './content.component.html',
  styleUrls: ['./content.component.css']
})
export class ContentComponent {

  subscription!: Subscription;

  constructor(private loginService: LoginService) { }

  onLogin(input: any): void {
    const userLogin: Login = {
      "login": input.login, 
      "password": input.password
    };

    this.subscription = this.loginService.userLogin(userLogin).subscribe({
      // next: value => console.log('Observable emitted the next value: ' + value),
      // error: err => console.error('Observable emitted an error: ' + err),
      // complete: () => console.log('Observable emitted the complete notification')
    });
  }

  onRegister(input: any): void {
    const registerUser: Register = {
      "firstName": input.firstName,
      "lastName": input.lastName,
      "login": input.login,
      "password": input.password
    }

    this.subscription = this.loginService.registerUser(registerUser).subscribe({
      // next: value => console.log('Observable emitted the next value: ' + value),
      // error: err => console.error('Observable emitted an error: ' + err),
      // complete: () => console.log('Observable emitted the complete notification')
    });
  }

  ngOnDestroy(): void {
    // Unsubscribe from the observable to prevent memory leaks
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }

}
