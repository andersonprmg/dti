import { ModuleWithProviders }  from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
 
 
import { ConsultaComponent } from './app/produto/consulta/consulta.component';
 
import {CadastroComponent} from './app/produto/cadastro/cadastro.component';
 
import { HomeComponent } from './app/home/home.component';
 
const appRoutes: Routes = [
    { path: 'home',                    component: HomeComponent },
    { path: '',                        component: HomeComponent },
    { path: 'consulta-produto',        component: ConsultaComponent },
    { path: 'cadastro-produto',        component: CadastroComponent },
    { path: 'cadastro-produto/:id',    component: CadastroComponent }
 
];
 
export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes);