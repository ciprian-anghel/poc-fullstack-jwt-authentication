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
  componentToShow: string = "welcome";

  constructor(private loginService: LoginService) { }

  showComponent(componentToShow: string): void {
    this.componentToShow = componentToShow;
  }

  onLogin(input: any): void {
    const userLogin: Login = {
      "login": input.login, 
      "password": input.password
    };

    this.subscription = this.loginService.userLogin(userLogin).subscribe({
      next: value => { 
          this.loginService.setAuthToken(value.token);
          this.componentToShow = 'content';
          console.log('Login successful: ' + value);
      },
      error: err => console.error('Observable emitted an error: ' + err),
      complete: () => console.log('Observable emitted the complete notification')
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
      next: value => {
        this.loginService.setAuthToken(value.token);
        this.componentToShow = 'content';
        console.log('Registration successful: ' + value);
      }
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
