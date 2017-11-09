import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { appRoutes } from './routes';
import { AppComponent } from './app.component';
import { LogInComponent } from './log-in/log-in.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { HouseDetailsComponent } from './house-details/house-details.component';
import { SideBarComponent } from './side-bar/side-bar.component';
import { ServicesComponent } from './services/services.component';
import { NewServiceComponent } from './new-service/new-service.component';
import { RoomiesComponent } from './roomies/roomies.component';



@NgModule({
  declarations: [
    AppComponent,
    LogInComponent,
    SignUpComponent,
    HouseDetailsComponent,
    SideBarComponent,
    ServicesComponent,
    NewServiceComponent,
    RoomiesComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
