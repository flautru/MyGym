import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Home

 } from './pages/home/home';
import { Header } from "./component/header/header";
import { Footer } from "./component/footer/footer";
@Component({
  selector: 'app-root',
  imports: [RouterOutlet, Header, Footer],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected title = 'MyGym';
}
