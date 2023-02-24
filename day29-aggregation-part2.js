use shows

// $unwind
db.tv.aggregate([
    { $unwind: "$genres" },
    {
        $group: {
            _id: "$genres",
            total: { $sum: 1}        }    },
    {
        $sort: { _id: 1}    }
])

// $bucket
db.tv.aggregate([
    {
        $bucket: {
            groupBy: "$rating.average",
            boundaries: [3, 6, 9],
            default: ">9",
            output: {
                titles: { $push: "$name" }            }        }    }
])

use bgg

// $lookup
db.game.aggregate([
    { 
        $match: { 
            name: { $regex: "carcassonne", $options: "i" }
        }
    },
    {
        $lookup: {
            from: "comment",
            foreignField: "gid",
            localField: "gid",
            as: "comments"        }    },
    {
        $unwind: "$comments"    },
    {
        $sort: { "comments.rating": -1 }    },
    {
        $limit: 5    }
])