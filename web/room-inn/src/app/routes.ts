import { Routes } from '@angular/router'
import { LogInComponent } from './log-in/log-in.component'

export const appRoutes: Routes = [
  { path: 'login', component: LogInComponent },
  { path: '', redirectTo: '/login', pathMatch: 'full'}
]
