import insert from "./insert"
import remove from "./delete"
import update from "./update"
import upload from "./upload"
import getPages from "./getPages"
import deleteList from "./deleteList"

const admin = {
    modules:{
        insert,
        remove,
        update,
        upload,
        getPages,
        deleteList
    }
}

export default admin
