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

  onSubmit(loginData)
  {
    this.http.post('http://10.11.112.10:8080/api/auth',loginData).subscribe();
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
