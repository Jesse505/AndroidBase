package ${packageName}.presenter

import ${packageName}.contract.${contractName}Contract
import ${packageName}.model.${modelName}Model
import com.jesse.baselibs.base.BasePresenter

class ${presenterName}Presenter : BasePresenter<${contractName}Contract.View>(), ${contractName}Contract.Presenter {

    private val mModel by lazy {
        ${modelName}Model()
    }
}