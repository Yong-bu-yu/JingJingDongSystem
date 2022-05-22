import server from "../server";

export function ordersPages(params){
    return server.get('/orders/getPages',{
        params: params
    })
}
