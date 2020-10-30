<template>
  <div class="app-container">
    <!--查询框-->
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch">
      <el-form-item label="项目名称" prop="projectName">
        <el-input
          v-model="queryParams.projectName"
          placeholder="请输入项目名称"
          clearable
          size="small"
          style="width: 240px"
        />
      </el-form-item>
      <el-form-item label="测试负责人" prop="scriptId">
        <el-select v-model="queryParams.testId" size="small" placeholder="请选择开发负责人">
          <el-option
            v-for="item in testerOption"
            :key="item.userId"
            :label="item.nickName"
            :value="item.userId"
          ></el-option>
        </el-select>

        <!--<el-input
          v-model="queryParams.scriptId"
          placeholder="请输入脚本名称"
          clearable
          size="small"
          style="width: 240px"
        />-->
      </el-form-item>
      <el-form-item label="开发负责人" prop="developId">
        <el-select v-model="queryParams.developId" size="small" placeholder="请选择开发负责人">
          <el-option
            v-for="item in developerOption"
            :key="item.userId"
            :label="item.nickName"
            :value="item.userId"
          ></el-option>
        </el-select>
       <!-- <el-input
          v-model="queryParams.developId"
          placeholder="请输开发负责人名称"
          clearable
          size="small"
          style="width: 240px"
        />-->
      </el-form-item>
      <el-form-item>
        <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
      </el-form-item>
    </el-form>
    <!--批量操作按钮-->
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="dialogAdd"
        >新增项目
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
        >删除项目
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <!--内容表格-->
    <el-table v-loading="loading" ref="multipleTable" :data="projectList" row-key="id" :load="load"
              :tree-props="{children:'children',hasChildren:'hasChildren'}" lazy
              @selection-change="handleSelectionChange">
      <el-table-column type="selection" :selectable="checkboxT" width="55" align="center"/>
      <el-table-column label="项目编号" width="120">
        <template slot-scope="{row}">
          {{ row.id }}
        </template>
      </el-table-column>
      <el-table-column label="项目名称" prop="projectName" :show-overflow-tooltip="true" width="150"/>
      <el-table-column label="脚本名称" prop="scriptName" :show-overflow-tooltip="true" width="150"/>
      <el-table-column label="上传者" prop="creater" width="100"/>
      <el-table-column label="创建时间" align="center" prop="createTime" width="180"/>
      <el-table-column label="开发负责人" width="180">
        <template slot-scope="{row}">
          {{ row.developId | userNameFilter}}
        </template>
      </el-table-column>
      <el-table-column label="测试负责人" prop="testId" width="180">
        <template slot-scope="{row}">
          {{row.testId | userNameFilter}}
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <!--项目添加按钮-->
          <el-button
            size="mini"
            type="text"
            icon="el-icon-plus"
            v-if="scope.row.projectName"
            @click="scriptHandleAdd(scope.row)"
          >
            添加
          </el-button>
          <!--项目修改按钮-->
          <el-button
            size="mini"
            type="text"
            v-if="scope.row.projectName"
            icon="el-icon-edit"
            @click="projectUpdate(scope.row)"
          >修改
          </el-button>
          <!--脚本修改按钮-->
          <el-button
            size="mini"
            type="text"
            v-if="scope.row.scriptName"
            icon="el-icon-edit"
            @click="scriptUpdate(scope.row)"
          >修改
          </el-button>
          <!--项目删除按钮-->
          <el-button
            size="mini"
            type="text"
            v-if="scope.row.projectName"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
          >删除
          </el-button>
          <!--脚本删除按钮-->
          <el-button
            size="mini"
            type="text"
            v-if="scope.row.scriptName"
            icon="el-icon-delete"
            @click="scriptDelete(scope.row)"
          >删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <!--分页符-->
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!--对话框-项目add or update-->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="dataForm" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="项目名称" prop="projectName">
          <el-input v-model="form.projectName" placeholder="请输入项目名称"/>
        </el-form-item>
        <el-form-item label="开发负责人" prop="developId">
          <el-select v-model="form.developId">
            <el-option
              v-for="item in developerOption"
              :key="item.userId"
              :label="item.nickName"
              :value="item.userId"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="测试负责人" prop="testId">
          <el-select v-model="form.testId">
            <el-option
              v-for="item in testerOption"
              :key="item.userId"
              :label="item.nickName"
              :value="item.userId"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="项目备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!--对话框-脚本add or update-->
    <el-dialog :title="title" :model="upload" :visible.sync="scriptOpen" width="550px" append-to-body>
      <el-form  label-width="100px">
        <el-form-item label="项目名称" v-if="upload.projectName">
          <el-input disabled v-model="upload.projectName"></el-input>
        </el-form-item>
        <el-form-item label="上传脚本">
          <el-upload
            ref="upload"
            :limit="1"
            accept=".py"
            :action="upload.url"
            :headers="upload.headers"
            :data="upload.data"
            :file-list="upload.fileList"
            :on-progress="handleFileUploadProgress"
            :on-success="handleFileSuccess"
            :auto-upload="false"
            drag
          >
            <i class="el-icon-upload"></i>
            <div class="el-upload__text">
              将文件拖到此处，或
              <em>点击上传</em>
            </div>
          </el-upload>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button :loading="upload.isUploading" type="primary" @click="submitFileForm">确 定</el-button>
        <el-button @click="scriptCancel">取 消</el-button>
      </div>
    </el-dialog>


  </div>
</template>

<script>
  import {getTester, getDevelop, addProject, listProject, updateProject, delProject} from '@/api/analysis/project';
  import {getScriptByPI, delScript} from '@/api/analysis/script';
  import {listUserName} from '@/api/system/user';
  import {getToken} from "@/utils/auth";

  let allName = [];
  export default {
    name: 'index',
    filters: {
      userNameFilter(userId) {
        const id = parseInt(userId);
        const userMessage = allName.find(item => item.userId === id);
        return userMessage ? userMessage.nickName : null
      }
    },
    data() {
      return {
        //数据加载标志
        loading: false,
        // 项目对话框是否显示
        open: false,
        // 脚本对话框的显示
        scriptOpen: false,
        // 对话框标题
        title: undefined,
        //测试人数据表
        testerOption: [],
        //开发人员数据表
        developerOption: [],
        // 选中数组
        ids: [],
        //项目对话框参数
        form: {
          // 项目id
          id: undefined,
          //项目名称
          projectName: undefined,
          //测试人员id
          testId: undefined,
          //开发人员id
          developId: undefined,
          //项目备注
          remark: undefined,
        },
        //脚本对话框参数
        upload: {
          //项目名称
          projectName: undefined,
          data: {
            //项目id
            projectId: undefined,
            //脚本id
            id: undefined
          },
          // 是否禁用上传
          isUploading: false,
          // 设置上传的请求头
          headers: {Authorization: "Bearer " + getToken()},
          // 上传的地址
          url: process.env.VUE_APP_BASE_API + "/analysis/script/importData",
          //上传的文件名称以及路径
          fileList: []
        },
        //项目信息列表
        projectList: [],
        //项目对应的子节点脚本信息
        maps: new Map(),
        //对话框表单验证
        rules: {
          projectName: [{required: true, message: '请输入项目名称', trigger: 'blur'}],
          testId: [{required: true, message: '请选择测试负责人', trigger: 'blur'}],
          developId: [{required: true, message: '请选择开发人员', trigger: 'blur'}]
        },
        // 查询数据总数
        total: 0,
        // 数据查询参数，分页参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          projectName: undefined,
          developId: undefined,
          scriptId: undefined
        },
        //  提交判断参数
        sign: 0,
        showSearch: true,
        // 非单个禁用
        single: true,
        //  非多个禁用
        multiple: true,
      }
    },
    //页面初始话加载数据
    created() {
      this.getList();
      this.searchName();
      this.getDeveloperSelect();
      this.getTesterSelect();
    },
    //JS方法
    methods: {
      //查询项目信息
      getList() {
        this.loading = true;
        const _self = this;
        //  根据条件进行用户信息的查询
        listProject(this.queryParams).then(response => {
          this.projectList = response.rows;
          // const temp = response.rows;
          //进行脚本信息是否存在的判断
          this.projectList.forEach(function (item, index) {
            _self.projectList[index].hasChildren = true;
            /*getScriptByPI(item.id).then(response => {


              /!*const data = response.data.length;
              _self.projectList = temp;
              if (data !== 0) {
                _self.projectList[index].hasChildren = true;
                _self.$forceUpdate();
              }*!/

            });*/
          });

          this.total = response.total;
          this.loading = false;
          this.$forceUpdate();
        })
      },
      handleQuery() {
        this.queryParams.page = 1;
        this.getList();
      },
      // 项目增加对话框
      dialogAdd() {
        this.reset();
        this.open = true;
        this.getDeveloperSelect();
        this.getTesterSelect();
        this.title = "添加项目";
        this.sign = 1;
      },
      //脚本增加对话框
      scriptHandleAdd(row) {
        this.scriptReset();
        this.upload.projectName = row.projectName;
        this.upload.data.projectId = row.id;
        this.scriptOpen = true;
        this.title = "添加脚本";
      },
      //项目信息提交
      submitForm: function () {
        this.$refs["dataForm"].validate(valid => {
          if (valid) {
            if (this.sign === 1) {
              addProject(this.form).then(response => {
                //项目信息添加成功
                this.msgSuccess(response.msg);
                this.open = false;
                this.getList()
              })
            } else if (this.sign === 2) {
              updateProject(this.form).then(response => {
                this.msgSuccess(response.msg);
                this.open = false;
                this.getList()
              })
            }
          }
        })
      },
      //项目信息修改按钮
      projectUpdate(row) {
        //  数据复制
        this.form = Object.assign({}, row);
        this.form.developId = parseInt(this.form.developId);
        this.form.testId = parseInt(this.form.testId);
        this.title = "项目修改";
        this.getDeveloperSelect();
        this.getTesterSelect();
        this.open = true;
        this.sign = 2;
      },
      //脚本信息修改按钮
      scriptUpdate(row) {
        // this.upload = Object.assign({},row);
        this.title = row.scriptName + "脚本修改";
        this.upload.data.projectId = row.projectId;
        this.upload.data.id = row.id;
        this.upload.url = process.env.VUE_APP_BASE_API + "/analysis/script/updateScript";
        this.scriptOpen = true;
      },
      // 项目删除按钮
      handleDelete(row) {
        const ids = row.id || this.ids;
        this.$confirm('是否确认删除项目编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function () {
          return delProject(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
      },
      // 脚本的信息删除
      scriptDelete(row) {
        this.$confirm('是否确认删除脚本为' + row.id + '的数据项？','警告',{
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function () {
          return delScript(row.id);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
          this.refreshLoadTree(row.projectId);
        });

      },
      // 项目对话框取消按钮
      cancel() {
        this.open = false;
        this.reset();
      },
      // 脚本对话框取消按钮
      scriptCancel() {
        this.scriptOpen = false;
        this.scriptReset();
      },
      //  表单数据重置
      reset() {
        this.form = {
          id: undefined,
          projectName: undefined,
          testerOption: [],
          developerOption: [],
          remark: undefined,
          testId: undefined,
          developId: undefined
        };
        //清除表单验证的信息
        this.resetForm("dataForm");
      },
      scriptReset() {
        this.upload = {
          projectName: undefined,
          data: {
            projectId: undefined,
            id: undefined,
          },
          isUploading: false,
          headers: {Authorization: "Bearer " + getToken()},
          url: process.env.VUE_APP_BASE_API + "/analysis/script/importData",
          fileList: []
        };
      },
      // 查询测试人员
      getTesterSelect() {
        getTester().then(response => {
          this.testerOption = response.data;
        })
      },
      //  查询开发人员
      getDeveloperSelect() {
        getDevelop().then(response => {
          this.developerOption = response.data
        })
      },
      // 多选框选中数据
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.id);
        this.multiple = !selection.length;
      },
      // 文件上传中处理
      handleFileUploadProgress(event, file, fileList) {
        this.upload.isUploading = true;
      },
      // 文件上传成功处理
      handleFileSuccess(response, file, fileList) {
        this.upload.isUploading = false;
        this.form.filePath = response.url;
        this.scriptOpen = false;
        this.msgSuccess(response.msg);
        const id = this.upload.data.projectId;
        this.refreshLoadTree(id)
      },
      // 脚本文件的提交
      submitFileForm() {
        this.$refs.upload.submit()
      },
      //  表中数据字节点数据异步加载
      load(tree, treeNode, resolve) {
        const projectId = tree.id;
        //将当前选中节点存储到maps中去
        this.maps.set(projectId, {tree, treeNode, resolve});
        //  根据projectId读取脚本的信息
        getScriptByPI(projectId).then(response => {
          const menuList = response.data;
          // console.log(menuList);
          resolve(menuList);
        })
      },
      //重新触发树形表格load函数
      refreshLoadTree(parentId){
        const {tree ,treeNode, resolve } = this.maps.get(parentId);
        this.$set(this.$refs.multipleTable.store.states.lazyTreeNodeMap,parentId,[]);
        if (tree){
          this.load(tree,treeNode,resolve)
        }
      },
      //  负责人名称，人员id与姓名查找
      searchName() {
        listUserName().then(response => {
          allName = response.data;
        })
      },
      checkboxT(row, rowIndex) {
        if (row.projectName != null) {
          return true;
        } else if (row.projectName === undefined) {
          return false;
        }
      },
      //  根据项目是否存在脚本进行hasChildren值的赋值
      setHasChildren(id, _self, index) {
        getScriptByPI(id).then(response => {
          const data = response.data.length;
          if (data !== 0) {
            if (index !== -1) {
              flag = true
            } else {
              flag = false
            }
          }
        });
      }
    }
  }
</script>

<style scoped>

</style>
