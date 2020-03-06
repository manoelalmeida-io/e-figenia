import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})

export class RegisterComponent implements OnInit {
  registerForm;

  onSubmit(registerData) {
    this.http.post('http://10.11.112.10:8080/api/users', registerData).subscribe();
  }

  constructor(private formBuilder: FormBuilder, private http: HttpClient) {
    this.registerForm = this.formBuilder.group({
      name: '',
      email: '',
      password: '',
      cpf: '',
      postalCode: ''
    });
  }

  ngOnInit(): void {
  }

}
