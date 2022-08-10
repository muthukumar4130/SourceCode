import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { HomeComponent } from "./home/home.component";
import { LoginComponent } from "./login/login.component";
import { RegisterComponent } from "./register/register.component";
import { UpdateregisterComponent } from "./updateregister/updateregister.component";
import { ViewregisterComponent } from "./viewregister/viewregister.component";

const routes: Routes=[
    {path:"register",component:RegisterComponent},
    {path:"home",component:HomeComponent},
    {path:"login",component:LoginComponent},
    {path:"viewregister",component:ViewregisterComponent},
    {path:"updateregister/:id",component:UpdateregisterComponent},
    {path:"",redirectTo:"/register",pathMatch:"full"}
];

@NgModule({
    imports:[RouterModule.forRoot(routes)],
    exports:[RouterModule]
})
export class AppRoutingModule{}