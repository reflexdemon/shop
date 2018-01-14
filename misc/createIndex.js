// mongo ds255787.mlab.com:55787/shop -u shopadmin -p testing123
if (db.counters) {
  print("Counters exist");
} else {
  print("Counters Does not exist");
  //create a counters collection
  db.createCollection("counters")
}

if (db.counters.find({_id: "productid"}).count() > 0) {
  print("Document productid exist");
} else {
  print("Document productid does not exist. So will insert.");
  // //insert sequence document in the counters collection
  db.counters.insert({_id: "productid", sequence_value: 0})
}


if (db.counters.find({_id: "orderid"}).count() > 0) {
  print("Document orderid exist");
} else {
  print("Document orderid does not exist. So will insert.");
  // //insert sequence document in the counters collection
  db.counters.insert({_id: "orderid", sequence_value: 0})
}


//create a function getNextSequenceValue which will take the sequence name as its input,
// increment the sequence number by 1 and return the updated sequence number
function getNextSequenceValue(sequenceName) {

  var sequenceDocument = db.counters.findAndModify({
    query: {_id: sequenceName},
    update: {$inc: {sequence_value: 1}},
    new: true
  });

  return sequenceDocument.sequence_value;
}

//Adding a stored procedure
db.system.js.save({
  _id: "getNextSequenceValue", value: function (sequenceName) {

    var sequenceDocument = db.counters.findAndModify({
      query: {_id: sequenceName},
      update: {$inc: {sequence_value: 1}},
      new: true
    });

    return sequenceDocument.sequence_value;
  }
});

db.eval("getNextSequenceValue('productid')");
db.eval("getNextSequenceValue('orderid')");

// //Create Index for text search
db.catalog.createIndex(
  {
    category: "text",
    name: "text",
    description: "text"
  }, {
    weights: {
      category: 5,
      name: 10,
      description: 10
    }
  });



