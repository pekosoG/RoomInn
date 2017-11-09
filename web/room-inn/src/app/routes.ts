import { Routes } from '@angular/router'
import { LogInComponent } from './log-in/log-in.component'
import { SignUpComponent } from './sign-up/sign-up.component'
import { HouseDetailsComponent } from './house-details/house-details.component'
import { ServicesComponent } from './services/services.component'

export const appRoutes: Routes = [
  { path: 'login', component: LogInComponent },
  { path: 'signup', component: SignUpComponent },
  { path: 'details', component: HouseDetailsComponent },
  { path: 'services', component: ServicesComponent },
  { path: '', redirectTo: '/login', pathMatch: 'full'}
]
