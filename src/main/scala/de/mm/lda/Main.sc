import scala.util.Random

type Token = Int
type Document = Int
type Topic = Int

val q = Map(
  0 -> Vector(0, 0, 0, 0, 0),
  1 -> Vector(1, 1, 1, 1, 1)
)
val k = 2
val random = Random

val y_0 = q.flatMap((document) => {
  val (documentId, tokens) = document
  tokens.map( (documentId, _, random.nextInt(k)) )
})

val emptyDocumentTokenTopicCounts: Map[(Document, Token, Topic), Int] = Map.empty
val c_dvk = y_0.foldLeft(emptyDocumentTokenTopicCounts)((documentTokenTopicCounts, currentToken) => {
  val newCount = documentTokenTopicCounts.get(currentToken).getOrElse(0) + 1
  documentTokenTopicCounts.updated(currentToken, newCount)
})

val emptyDocumentTopicCounts: Map[(Document, Topic), Int] = Map.empty
val c_dk = c_dvk.foldLeft(emptyDocumentTopicCounts)((documentTopicCounts, currentDocumentTokenTopicCount) => {
  val ((currentDocument, _, currentTopic), currentCount) = currentDocumentTokenTopicCount
  val newCount = documentTopicCounts.get((currentDocument, currentTopic)).getOrElse(0) + currentCount
  documentTopicCounts.updated((currentDocument, currentTopic), newCount)
})
