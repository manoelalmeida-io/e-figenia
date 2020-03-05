import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})

export class RegisterComponent implements OnInit {

  onSubmit(f: NgForm) {
    this.http.post('http://10.11.112.10/api/users', f.value).subscribe();
  }

  constructor(private http: HttpClient) {
  }

  ngOnInit(): void {
  }

}
