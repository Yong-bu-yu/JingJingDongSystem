import message from "element-plus/es/el-message";
import {DeleteList} from "../../utils/admin/deleteList";

const deleteList = {
    actions: {
        // eslint-disable-next-line no-unused-vars
        async AdminDeleteList({commit},param){
            await DeleteList(param)
                // eslint-disable-next-line no-unused-vars
                .then(r => {
                    message({
                        type: 'success',
                        message: '数据删除成功'
                    })
                    // eslint-disable-next-line no-unused-vars
                }).catch(r =>{
                    console.log(r)
                    message({
                        type: "error",
                        message: "删除错误"
                    })
                })
        }
    }
}

export default deleteList
