/***
 GLobal Filter
 ***/

// Route State Load Spinner(used on page or content load)
angular.module('myApp.common.filter',[])

    //菜单列表过滤器
    .filter('parentMenu',function () {
        return function (inputArray) {
            var outArray = [];
            angular.forEach(inputArray, function(data,index,array){
                if(data.tbMenu.parent =='#'){
                    outArray.push(data);
                }
            });
            return outArray;
        }
    })

    .filter('childMenu',function () {
        return function (inputArray,parentID) {
            var outArray = [];
            angular.forEach(inputArray, function(data,index,array){
                if(data.tbMenu.parent ==parentID){
                    outArray.push(data);
                }
            });
            return outArray;
        }
    });
