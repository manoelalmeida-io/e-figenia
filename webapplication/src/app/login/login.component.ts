import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm;
  userToken: any;

  onSubmit(loginData)
  {
    this.http.post('http://10.11.112.10:8080/api/auth',loginData, {observe: 'response'})
    .subscribe(
      user => {
        if (user) {
          this.userToken = user.headers.get('authorization');
          localStorage.setItem('user', JSON.stringify(this.userToken));
          JSON.parse(localStorage.getItem('user'));
        } else {
          localStorage.setItem('user', null);
          JSON.parse(localStorage.getItem('user'));
        }
      });
  }

  constructor(private formBuilder: FormBuilder, private http: HttpClient) {
    this.loginForm = this.formBuilder.group({
      email: '',
      password: ''
    });
   }

  ngOnInit(): void {
  }

}
