import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { Headers} from '@angular/http';
import { RequestOptions } from '@angular/http';
 
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/do';
import 'rxjs/add/operator/catch';
//import { Observable } from 'rxjs/Rx';
import { Observable } from 'rxjs/Observable';

import {Produto} from '../services/produto';
import {ConfigService} from './config.service';
 
@Injectable()
export class ProdutoService {
 
    private baseUrlService:string = '';
    private headers:Headers;
    private options:RequestOptions;
 
    constructor(private http: Http,
                private configService: ConfigService) { 
 
        /**SETANDO A URL DO SERVIÇO REST QUE VAI SER ACESSADO */
        this.baseUrlService = configService.getUrlService() + '/produto/';
 
        /*ADICIONANDO O JSON NO HEADER */
        this.headers = new Headers({ 'Content-Type': 'application/json;charset=UTF-8' });                
        this.options = new RequestOptions({ headers: this.headers });
    }
 
    /**CONSULTA TODOS OS PRODUTOS */
    getProdutos(){        
        return this.http.get(this.baseUrlService).map(res => res.json());
    }
 
    /**ADICIONA NOVO PRODUTO */
    addProduto(produto: Produto){
 
        return this.http.post(this.baseUrlService, JSON.stringify(produto),this.options)
        .map(res => res.json());
    }
    /**EXCLUI UM PRODUTO */
    excluirProduto(codigo:number){
 
        return this.http.delete(this.baseUrlService + codigo).map(res => res.json());
    }
 
    /**CONSULTA UM PRODUTO PELO CÓDIGO */
    getProduto(codigo:number){
 
        return this.http.get(this.baseUrlService + codigo).map(res => res.json());
    }
 
    /**ATUALIZA INFORMAÇÕES DAO PRODUTO */
    atualizarProduto(produto:Produto){
 
        return this.http.put(this.baseUrlService +  produto.id, JSON.stringify(produto),this.options)
        .map(res => res.json());
    }
 
}