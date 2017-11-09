import { Routes } from '@angular/router'
import { LogInComponent } from './log-in/log-in.component'
import { SignUpComponent } from './sign-up/sign-up.component'
import { HouseDetailsComponent } from './house-details/house-details.component'
import { ServicesComponent } from './services/services.component'
import { NewServiceComponent } from './new-service/new-service.component'
import { RoomiesComponent } from './roomies/roomies.component'


export const appRoutes: Routes = [
  { path: 'login', component: LogInComponent },
  { path: 'signup', component: SignUpComponent },
  { path: 'details', component: HouseDetailsComponent },
  { path: 'services', component: ServicesComponent },
  { path: 'newService', component: NewServiceComponent },
  { path: 'roomies', component: RoomiesComponent },
  { path: '', redirectTo: '/login', pathMatch: 'full'}
]
