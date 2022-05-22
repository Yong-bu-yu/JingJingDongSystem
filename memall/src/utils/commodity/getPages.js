import server from "../server";

export function Pages(params){
    return server.get('/commodity/getPages',{
        params: params
    })
}
