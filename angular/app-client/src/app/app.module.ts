import { NgModule, LOCALE_ID} from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { DirectivaComponent } from './directiva/directiva.component';
import { ClientesComponent } from './clientes/clientes.component';
import { ClienteService } from './clientes/cliente.service';
import { RouterModule, Routes } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { FormComponent } from './clientes/form.component';
import { FormsModule } from '@angular/forms';
import localeES from '@angular/common/locales/es';
import { registerLocaleData } from '@angular/common';
import { PaginatorComponent } from './paginator/paginator.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatNativeDateModule} from '@angular/material/core';
import{MatDatepickerModule} from '@angular/material/datepicker';
import { DetalleComponent } from './clientes/detalle/detalle.component';
registerLocaleData(localeES, 'es');

const routes: Routes = [
  { path: '', redirectTo: '/clientes', pathMatch: 'full' },
  { path: 'directivas', component: DirectivaComponent },
  { path: 'clientes', component: ClientesComponent },
  { path: 'clientes/page/:page', component: ClientesComponent },
  { path: 'clientes/form', component: FormComponent },
  { path: 'clientes/form/:id', component: FormComponent }
  //{ path: 'clientes/ver/:id', component: DetalleComponent }
  
]

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    DirectivaComponent,
    ClientesComponent,
    FormComponent,
    PaginatorComponent,
    DetalleComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    RouterModule.forRoot(routes),
    HttpClientModule,
    FormsModule,
    BrowserAnimationsModule,
    MatDatepickerModule, MatNativeDateModule
  ],
  providers: [ClienteService, {provide: LOCALE_ID, useValue: 'es'}, MatNativeDateModule],
  bootstrap: [AppComponent]
})
export class AppModule { }
