package com.example.gorgullebelle.app.data

val formatPrompt = listOf(
    Message("system", "The response format must include and only include the following: 4 parts: 1 explanation, 1 mainText, 4 answers, and 4 scores. " +
            "The response must be exactly in the following format:"),
    Message("system", "explanation: \"Turkish explanation part of the question\""),
    Message("system", "mainText: \"The question main part in English\""),
    Message("system", "answer1: \"Answer option 1\""),
    Message("system", "score1: \"Score for answer option 1\""),
    Message("system", "answer2: \"Answer option 2\""),
    Message("system", "score2: \"Score for answer option 2\""),
    Message("system", "answer3: \"Answer option 3\""),
    Message("system", "score3: \"Score for answer option 3\""),
    Message("system", "answer4: \"Answer option 4\""),
    Message("system", "score4: \"Score for answer option 4\"")
)

val commonQuestionPrompts = listOf(
    Message("system", "Generate a question based on the concept provided above." +
            "Provide four answer options with scores ranging from -2 to 2, indicating their correctness. " +
            "The topic of the question should be General" +
            "Do not add any explanations or embellishments, your response should only be in the required format.")
) + formatPrompt


val konuTespitiPrompts = listOf(
    Message("system", "In the mainText field: Provide a slightly long English text, such as a passage from a book, " +
            "a wiki page, or an article. " +
            "should be in English."),

    Message("system", "In the explanation field: Provide a hint about the text's content, " +
            "but do not reveal the answer explicitly."+
            "should be in Turkish"),

    Message("system", "The answer options should be Turkish, like concepts, names, or terms related to the text. " +
            "The most relevant concept should receive the highest score, while irrelevant concepts can " +
            "be used to mislead slightly, but not make the question too difficult." +
            "The answers should be in Turkish.")
) + commonQuestionPrompts

val gereklilikTespitiPrompts = listOf(
    Message("system", "In the mainText field: Describe a situation or event in English . " +
            "This situation should present a problem, oddity, or issue that the user needs to understand and find a solution for." +
            "should be in English"),

    Message("system", "In the explanation field: Provide hints about what the problem might be. " +
            "should be in Turkish."),

    Message("system", "The answer options should be various items, actions, institutions, etc., " +
            "that could solve the problem described. These options could include things like \"fire extinguisher\" if there is a fire" +
            ", \"headphones\" if the issue is needing to listen to music in a library, and so on." +
            "The answers should be in Turkish.")
) + commonQuestionPrompts

val uygunEylemPrompts = listOf(
    Message("system", "In the mainText field: Provide dialogues in English in the mainText field. " +
            "These dialogues should involve either conversations with the user or between other characters." +
            "should be in English."),

    Message("system", "In the explanation field: Describe the context of the conversations, " +
            "such as where they are taking place, who is involved, what time it is, and other relevant details. " +
            "should be in Turkish."),

    Message("system", "The answer options should be actions in Turkish, such " +
            "as \"run away,\" \"nod your head no,\" \"smile,\" \"call the police,\" \"go to look,\" etc. " +
            "These actions should be contextually appropriate based on the dialogues provided." +
            "The answers should be in Turkish."),
) + commonQuestionPrompts

val questionPrompts = listOf(konuTespitiPrompts, gereklilikTespitiPrompts, uygunEylemPrompts)
