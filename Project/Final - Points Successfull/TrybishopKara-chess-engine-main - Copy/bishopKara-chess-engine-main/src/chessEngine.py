"""
    This class will be responsible for storing all the information about the current state of the chess game.
    It will also generate all the valid moves from the current state.
    It will also contain a log of all the moves played till the current state.
"""
from typing import Counter


class GameState():
    def __init__(self):
        # The chessboard is an 8 by 8 2D list, each element of the list has 2 characters
        # A sqaure with a chess piece on it is represented by a string of two alphabets while an empty square with no piece is represented by the string "--"
        # The first character of a chess piece represents the color of the piece 'w' - white, 'b' - black
        # And the second character represents the type of the piece 'p' - pawn, 'R' - rook, 'N' - Knight, 'B' - Bishop 'Q' - Queen, 'K' - King
        self.board = [
            ["--", "--", "--", "--", "bK","--", "--", "--"],
            ["--", "--", "--", "--", "--", "--", "--", "--"],
            ["--", "--", "--", "--", "--", "--", "--", "--"],
            ["--", "--", "--", "--", "--", "--", "--", "--"],
            ["--", "--", "--", "--", "--", "--", "--", "--"],
            ["--", "--", "--", "--", "--", "--", "--", "--"],
            ["--", "--", "--", "--", "--", "--", "--", "--"],
            ["wR", "--", "--", "--", "wK", "--", "--", "--"]
        ]
        self.moveFunctions = {'R' : self.getRookMoves,'K' : self.getKingMoves}
        self.whiteToMove = True
        self.movesLog = []
        self.whiteKingLocation = (7,4)
        self.blackKingLocation = (0,4)
        self.inCheck = False
        self.pins = []
        self.checks = []
        
        self.checkmate = False   
        self.stalemate = False
        
    '''
        Takes a move and executes it
    '''
    def makeMove(self, move):
        self.board[move.startRow][move.startCol] = "--"
        self.board[move.endRow][move.endCol] = move.pieceMoved
        self.movesLog.append(move) # log the move so it can be used to undo if needed
        self.whiteToMove = not self.whiteToMove # opposite player's turn
        if move.pieceMoved == "wK":
            self.whiteKingLocation = (move.endRow, move.endCol)
        elif move.pieceMoved == "bK":
            self.blackKingLocation = (move.endRow, move.endCol)
        

      

   
    '''
        Undo the last move
    '''
    def undoMove(self):
        if len(self.movesLog) != 0: #there should be some move
            move = self.movesLog.pop()
            self.board[move.startRow][move.startCol] = move.pieceMoved
            self.board[move.endRow][move.endCol] = move.pieceCaptured
            self.whiteToMove = not self.whiteToMove
            if move.pieceMoved == "wK":
                self.whiteKingLocation = (move.startRow, move.startCol)
            elif move.pieceMoved == "bK":
                self.blackKingLocation = (move.startRow, move.startCol)

           
        

            
           
            self.checkmate = False
            self.stalemate = False

    '''
        All moves considering checks(King Under Attack)
    '''
    def getValidMoves(self):
        
        moves = []
        self.inCheck , self.pins , self.checks = self.checkForPinsAndChecks()
        if self.whiteToMove:
            kingRow = self.whiteKingLocation[0]
            kingCol = self.whiteKingLocation[1]
        else:
            kingRow = self.blackKingLocation[0]
            kingCol = self.blackKingLocation[1]
        
        if self.inCheck:
            if len(self.checks) == 1: # only 1 check -> block check or move kings
                moves = self.getAllPossibleMoves()
                
                # to block one of piece should be between kings and enemy's piece
                check = self.checks[0]
                checkRow = check[0]
                checkCol = check[1]
                pieceChecking = self.board[checkRow][checkCol]
                validSquares = [] # block where piece can move
                if pieceChecking == 'N': # if it's knight we must capture knight
                    validSquares = [(checkRow , checkCol)]
                else:
                    for i in range(1,8):
                        validSquare = (kingRow + check[2]*i , kingCol + check[3]*i)
                        validSquares.append(validSquare)
                        if validSquare[0] == checkRow and validSquare[1] == checkCol:
                            break
                # get rid of the moves that don't block check or move king
                for i in range(len(moves)-1 , -1 , -1):
                    if moves[i].pieceMoved[1] != 'K':
                        if not (moves[i].endRow , moves[i].endCol) in validSquares:
                            moves.remove(moves[i])
            else: # double checks
                self.getKingMoves(kingRow , kingCol , moves)
        else: # no checks means all are valid
            moves = self.getAllPossibleMoves()
            
        if len(moves) == 0:
            if self.inCheck:
                self.checkmate = True
            else:
                self.stalemate = True

        return moves

    '''
        Determine if the current player is in check
    '''

    def inCheck(self):
        if self.whiteToMove:
            return self.squareUnderAttack(self.whiteKingLocation[0], self.whiteKingLocation[1])
        else:
            return self.squareUnderAttack(self.blackKingLocation[0], self.blackKingLocation[1])

    '''
        Determine if the enemy can attack the square(row, col)
    '''

    def squareUnderAttack(self, row, col):
        self.whiteToMove = not self.whiteToMove # switch to opponent's point of view
        oppMoves = self.getAllPossibleMoves()
        self.whiteToMove = not self.whiteToMove # switch back to original turn
        for move in oppMoves:
            if move.endRow == row and move.endCol == col: # square is under attack
                return True
        return False

    '''
        All moves without considering checks(King Under Attack)
    '''
    def getAllPossibleMoves(self):
        moves = []
        for row in range(len(self.board)):
            for col in range(len(self.board[row])):
                turn = self.board[row][col][0]
                if (turn == 'b' and not self.whiteToMove) or (turn == 'w' and self.whiteToMove):
                    piece = self.board[row][col][1]
                    self.moveFunctions[piece](row,col,moves)
        return moves


    '''
        Get all the possible moves for rook 
    '''
    def getRookMoves(self , row , col ,moves):
        piecePinned = False
        pinDirection = ()
        for i in range(len(self.pins) - 1 , -1 , -1):
            if self.pins[i][0] == row and self.pins[i][1] == col:
                piecePinned = True
                pinDirection = (self.pins[i][2] , self.pins[i][3])
                if self.board[row][col][1] != 'Q': # can't remove Queen
                    self.pins.remove(self.pins[i])
                break

        directions = [(-1,0) , (1,0) , (0,-1) , (0,1)]
        enemyColor = 'b' if self.whiteToMove else 'w'
        for d in directions:
            for i in range(1,8):
                endRow = row + d[0]*i
                endCol = col + d[1]*i
                if endRow>=0 and endRow<8 and endCol>=0 and endCol<8:
                    if not piecePinned or pinDirection == d or pinDirection == (-d[0] , -d[1]):
                        endPiece = self.board[endRow][endCol]
                        if endPiece == '--':
                            moves.append(Move((row,col) , (endRow , endCol) , self.board))
                        elif endPiece[0] == enemyColor:   # if we capture then we need to break 
                            moves.append(Move((row,col) , (endRow , endCol) , self.board))
                            break
                        else: # if our piece is there then also we need to break
                            break
                else:
                    break

    '''
        Get all the possible moves for king 
    '''
    def getKingMoves(self , row , col ,moves):
        rowMoves = [-1,-1,-1,0,0,1,1,1]
        colMoves = [-1,0,1,-1,1,-1,0,1]
        ourColor = 'w' if self.whiteToMove else 'b'
        for m in range(8):
            endRow = row + rowMoves[m]
            endCol = col + colMoves[m]
            if endRow>=0 and endRow<8 and endCol>=0 and endCol<8:
                endPiece = self.board[endRow][endCol]
                if endPiece[0] != ourColor: # not our piece

                    #place the king on the square and check if it would receive a check
                    if ourColor == 'w':
                        self.whiteKingLocation = (endRow , endCol)
                    else:
                        self.blackKingLocation = (endRow , endCol)
                    inCheck , pins , checks = self.checkForPinsAndChecks()

                    if not inCheck:
                        moves.append(Move((row,col) , (endRow , endCol) , self.board))

                    # placing king back on it's location
                    if ourColor == 'w':
                        self.whiteKingLocation = (row , col)
                    else:
                        self.blackKingLocation = (row , col)    



    
    def checkForPinsAndChecks(self):
        pins = []
        checks = []
        inCheck = False
        if self.whiteToMove:
            enemyColor = 'b'
            ourColor = 'w'
            startRow = self.whiteKingLocation[0]
            startCol = self.whiteKingLocation[1]
        else:
            enemyColor = 'w'
            ourColor = 'b'
            startRow = self.blackKingLocation[0]
            startCol = self.blackKingLocation[1]
        
        directions = [(-1,0) , (0,-1) , (1,0) , (0,1) , (-1,-1) , (-1,1) , (1,-1) , (1,1)]
        for j in range(len(directions)):
            d = directions[j]
            possiblePin = ()
            for i in range(1,8):
                endRow = startRow + d[0]*i
                endCol = startCol + d[1]*i
                if endRow>=0 and endRow<8 and endCol>=0 and endCol<8:
                    endPiece = self.board[endRow][endCol]
                    if endPiece[0] == ourColor and endPiece[1] != 'K':
                        if possiblePin == (): # 1st pin
                            possiblePin = (endRow , endCol , d[0] , d[1])
                        else: # 2nd our piece then 1st one can not be pin
                            break
                    elif endPiece[0] == enemyColor:
                        type = endPiece[1]
                        # 5 possibilities
                        # 1 rook : orthogonally away from king 
                        # 2 bishop : diagonally away
                        # 3 pawn : 1 square diagonally away
                        # 4 queen : any direction
                        # 5 king : 1 square away in any direction
                        if (0<=j<=3 and type == 'R') or (i==1 and type == 'K'):
                            if possiblePin == ():
                                inCheck = True
                                checks.append((endRow , endCol , d[0] , d[1]))
                                break
                            else: # if it's pins
                                pins.append(possiblePin)
                                break
                        else:
                            break 
                else: #off-board
                    break
        
        return inCheck , pins , checks
    

    

class Move():

    # Mapping to map row and column to rank and file respectively
    ranksToRows = {"8" : 0, "7" : 1, "6" : 2, "5" : 3, "4" : 4, "3" : 5, "2" : 6, "1" : 7}
    rowsToRanks = {rows : ranks for ranks, rows in ranksToRows.items()}

    filesToCols = {"a" : 0, "b" : 1, "c" : 2, "d" : 3, "e" : 4, "f" : 5, "g" : 6, "h" : 7}
    colsToFiles = {cols : files for files, cols in filesToCols.items()}


    def __init__(self, startSquare, endSquare, board, isEnPassantMove = False , isCastleMove = False):
        self.startRow = startSquare[0]
        self.startCol = startSquare[1]
        self.endRow = endSquare[0]
        self.endCol = endSquare[1]
        self.pieceMoved = board[self.startRow][self.startCol]
        self.pieceCaptured = board[self.endRow][self.endCol]
        

        
        self.moveID = self.startRow * 1000 + self.startCol * 100 + self.endRow * 10 + self.endCol
        
    """
        overriding the equals method
    """
    def __eq__(self, o: object) -> bool:
        if isinstance(o , Move):
            return self.moveID == o.moveID
        return False

    def getChessNotation(self):
        return self.getRankFile(self.startRow, self.startCol) + self.getRankFile(self.endRow, self.endCol)

    def getRankFile(self, row, col):
        return self.colsToFiles[col] + self.rowsToRanks[row]
