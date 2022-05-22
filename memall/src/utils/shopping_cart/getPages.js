import server from "../server";

export function shoppingCartPages(params){
    return server.get('/shoppingCart/getPages',{
        params: params
    })
}
